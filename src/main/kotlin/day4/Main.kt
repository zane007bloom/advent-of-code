package day4

import java.io.File

fun main(args: Array<String>) {
    val lines = File("/home/zane/IdeaProjects/advent-of-code/src/main/kotlin/input.txt").readLines()
    var sum = 0
    val validator = PassPhraseValidator()
    for (line in lines) {
        if (validator.validateWithAnagrams(line.split(Regex("\\s+")))) sum++
    }
    print(sum)
}