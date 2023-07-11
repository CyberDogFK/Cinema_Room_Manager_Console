fun main() {
    val totalSeconds = System.currentTimeMillis() / 1000 // do not change this line
    val totalHours = totalSeconds / 3600
    val hours = totalHours % 24
    val totalMunites = totalSeconds / 60
    val minutes = totalMunites % 60
    val seconds = totalSeconds - totalHours * 3600 - minutes * 60

    println("$hours:$minutes:$seconds")
}