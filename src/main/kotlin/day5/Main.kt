package day5

import java.io.File

fun main(args: Array<String>) {
    val jumps = File("/home/zane/IdeaProjects/advent-of-code/src/main/kotlin/input.txt").readLines().map { it.toInt() }.toMutableList()
    print(EscapeHelper().escapeNewRules(jumps))
}