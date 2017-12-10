package day10

import java.io.File

fun main(args: Array<String>) {
    val lengths = File("C:/Development/advent-of-code/src/main/kotlin/input.txt").readText().map { it.toInt() }.toMutableList()
    lengths.addAll(listOf(17, 31, 73, 47, 23))
    val numbers = KnotTyingHashEncoder().encode(lengths)
    print(numbers)
}
