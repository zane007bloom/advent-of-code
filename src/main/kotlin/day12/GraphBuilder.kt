package day12

class GraphBuilder {

    fun build(input: List<List<Int>>): Map<Int, Node> {
        val nodes: MutableMap<Int, Node> = mutableMapOf()

        for (inputLine in input) {
            val nodeId = inputLine[0]
            val connectedNodeIds = inputLine.subList(1, inputLine.size)
            val node = nodes.getOrDefault(nodeId, Node(nodeId))
            nodes.put(nodeId, node)

            for (connectedNodeId in connectedNodeIds) {
                if (connectedNodeId != nodeId) {
                    if (nodes.containsKey(connectedNodeId)) {
                        node.neighbours.add(nodes.getValue(connectedNodeId))
                    } else {
                        val connectedNode = Node(connectedNodeId)
                        nodes.put(connectedNodeId, connectedNode)
                        node.neighbours.add(connectedNode)
                    }
                }
            }
        }

        return nodes
    }

    fun visitNodes(root: Node, visitedNodes: MutableList<Node>) {
        visitedNodes.add(root)
        for (neighbour in root.neighbours) {
            if (!visitedNodes.contains(neighbour)) visitNodes(neighbour, visitedNodes)
        }
    }

//    fun findConnectedNodes(root: Node): List<Node> {
//
//    }

}