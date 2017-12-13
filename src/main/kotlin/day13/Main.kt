package day13

import java.io.File

fun main(args: Array<String>) {
    val lines = File("C:/Development/advent-of-code/src/main/kotlin/input.txt").readLines()
    val layers = mutableMapOf<Int, Int>()
    for (line in lines) {
        val layer = line.split(": ")[0].toInt()
        val depth = line.split(": ")[1].toInt()
        layers.put(layer, depth)
    }
    print(OptimizedFirewall().calculateDelay(layers))
}