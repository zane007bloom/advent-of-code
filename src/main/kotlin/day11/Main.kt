package day11

import java.io.File

fun main(args: Array<String>) {
    val directions = File("/home/zane/IdeaProjects/advent-of-code/src/main/kotlin/input.txt").readText().split(",")
    val distances = DistanceCalculator().calculate(directions)
    println(distances)
    var sum = 0
    for (distance in distances) {
        sum += distance.value
    }
    print(sum)
}
