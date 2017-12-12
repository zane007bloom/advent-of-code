package day12

import java.io.File

fun main(args: Array<String>) {
    val input = File("/home/zane/IdeaProjects/advent-of-code/src/main/kotlin/input.txt").readLines().map { it.split(", ", " <-> ").map { it.toInt() } }
    val graphBuilder = GraphBuilder()
    val nodes = graphBuilder.build(input)
    val visitedNodes = mutableListOf<Node>()
    graphBuilder.visitNodes(nodes.getValue(0), visitedNodes)
    println(visitedNodes.size)

    var keysNotInGraph = nodes.keys.minus(visitedNodes.map { it.id })
    var sum = 1

    while (keysNotInGraph.isNotEmpty()) {
        for (nodeId in keysNotInGraph) {
            val node = nodes.getValue(nodeId)
            if (!visitedNodes.contains(node)) {
                graphBuilder.visitNodes(node, visitedNodes)
                sum++
            }
        }
        keysNotInGraph = keysNotInGraph.minus(visitedNodes.map { it.id })
    }

    println(sum)
}
