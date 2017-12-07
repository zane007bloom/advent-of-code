package day7

import java.io.File

fun main(args: Array<String>) {
    val fileLines = File("/home/zane/IdeaProjects/advent-of-code/src/main/kotlin/input.txt").readLines()
    val lines: MutableList<MutableList<String>> = mutableListOf()
    for (line in fileLines) {
        val list = line.split(" ", "(", ")", "->", ",").toMutableList()
        list.removeIf({ a -> a.isNullOrBlank() })
        lines.add(list)
    }
    val tree = TreeBuilder().build(lines)
    print(tree)

}