package day19

import java.io.File

fun main(args: Array<String>) {
    val lines = File("C:/Development/advent-of-code/src/main/kotlin/input.txt").readLines()
    println()
    print(Route().traverse(lines))
    println()
    print(Route().traverseCountSteps(lines))
    println()
}