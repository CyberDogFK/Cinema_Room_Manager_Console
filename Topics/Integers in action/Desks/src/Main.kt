import java.math.RoundingMode
import kotlin.math.roundToInt

fun main() {
    // put your code here
    val firstGroup = readln().toInt(10)
    val secondGroup = readln().toInt(10)
    val thirdGroup = readln().toInt(10)

    val firstGroupTables = calculateTables(firstGroup)
    val secondGroupTables = calculateTables(secondGroup)
    val thirdGroupTables = calculateTables(thirdGroup)

    println(firstGroupTables + secondGroupTables + thirdGroupTables)
}

fun calculateTables(groupNumber: Int): Int {
    return (groupNumber / 2) + (groupNumber % 2)
}