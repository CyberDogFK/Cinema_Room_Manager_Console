package cinema

private const val MAX_PRICE = 10
private const val BIG_CAPACITY = 60
private const val MIN_PRICE = 8

val cinemaTwoDimensionArray = { rows: Int, seats: Int ->
    Array(rows) { Array(size = seats, init = {"S"})}
}

private var numberOfPurchasedTickets = 0
private var currentIncome = 0

fun main() {
    val input = readInputCinemaSize()
    val rows = input.first
    val seats = input.second
    val cinema = cinemaTwoDimensionArray(rows, seats)
    var exit = false
    while (!exit) {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when (readln().toInt(10)) {
            1 -> printCinema(cinema)
            2 -> buyTicket(cinema)
            3 -> showStatistic(cinema)
            0 -> exit = true
            else -> println("Wrong input!")
        }
    }
}

fun showStatistic(cinema: Array<Array<String>>) {
    println("Number of purchased tickets: $numberOfPurchasedTickets")
    val cinemaSize = cinema.size * cinema[0].size
    println("Percentage: ${"%.2f".format(numberOfPurchasedTickets.toDouble() / cinemaSize.toDouble() * 100.00)}%")
    println("Current income: $$currentIncome")
    println("Total income: $${calculateTotalIncome(cinema.size, cinema[0].size)}")
}

fun buyTicket(cinema: Array<Array<String>>) {
    println("Enter a row number:")
    val row = readln().toInt(10)
    println("Enter a seat number in that row:")
    val seat = readln().toInt(10)
    runCatching {
        if (cinema[row - 1][seat - 1] == "S") {
            numberOfPurchasedTickets++
            cinema[row - 1][seat - 1] = "B"
            val price = getTicketPrice(cinema.size, cinema.size * cinema[0].size, row)
            currentIncome += price
        } else {
            println("That ticket has already been purchased!")
            buyTicket(cinema)
        }
    }.onFailure {
        println("Wrong input!")
        buyTicket(cinema)
    }
}

fun getTicketPrice(rows: Int, size: Int, row: Int): Int {
    val price = when {
        size < BIG_CAPACITY -> MAX_PRICE
        else -> {
            val half = rows / 2
            when {
                row <= half -> MAX_PRICE
                else -> MIN_PRICE
            }
        }}
    println()
    println("Ticket price: $$price")
    return price
}

fun printCinema(cinema: Array<Array<String>>) {
    println("Cinema: ")
    print(" ")
    for (i in 1 .. cinema[0].size){
        print(" $i")
    }
    println()
    for ((i, r) in cinema.withIndex()) {
        print("${i + 1}")
        for (s in r) {
            print(" $s")
        }
        println()
    }
}

fun calculateTotalIncome(rows: Int, seats: Int): Int {
    val totalNumber = rows * seats
    return when  {
        totalNumber < 60 -> totalNumber * 10
        else -> calculateTotalIncomeForLargeRoom(rows, seats)
    }
}

fun calculateTotalIncomeForLargeRoom(rows: Int, seats: Int): Int {
    val firstHalf = rows / 2 * seats
    val secondHalf = ((rows / 2) + rows % 2) * seats
    val priceForFirstHalf = firstHalf * 10
    val priceForSecondHalf = secondHalf * 8
    return priceForFirstHalf + priceForSecondHalf
}

fun readInputCinemaSize(): Pair<Int, Int> {
    println("Enter the number of rows: ")
    val rows = readln().toInt(10)
    println("Enter the number of seats in each row: ")
    val seats = readln().toInt(10)
    return Pair(rows, seats)
}