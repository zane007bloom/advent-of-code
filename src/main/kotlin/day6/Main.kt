package day6

import java.io.File

fun main(args: Array<String>) {
    val banks = File("/home/zane/IdeaProjects/advent-of-code/src/main/kotlin/input.txt").readText().split(Regex("\\s+")).map { it.toInt() }
    print(MemoryAllocator().allocateFindLastSeenLength(banks))
}