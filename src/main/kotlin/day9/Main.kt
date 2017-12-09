package day9

import java.io.File

fun main(args: Array<String>) {
    val input = File("C:/Development/advent-of-code/src/main/kotlin/input.txt").readText()
    print(GarbageStreamGroupCalculator().calculateNumberOfRandomChars(input))
}


