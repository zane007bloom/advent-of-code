package day1

import java.io.File

fun main(args: Array<String>) {
    val digits = File("/home/zane/IdeaProjects/advent-of-code/src/main/kotlin/input.txt").readText().toCharArray().map { it.toInt() - 48 }
//    print(CapthaAdder().add(digits))
    print(CapthaAdder().addHalfForward(digits))
}
