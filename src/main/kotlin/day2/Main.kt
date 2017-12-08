package day2

import java.io.File

fun main(args: Array<String>) {
    val lines = File("C:/Development/advent-of-code/src/main/kotlin/input.txt").readLines()
    var sum = 0
    val differenceDeterminer = DifferenceDeterminer()
    for (line in lines) {
        val numbers = line.trim().split("\\s+").map { it.toInt() }
        sum += differenceDeterminer.determineModulus(numbers)
    }
    print(sum)

}