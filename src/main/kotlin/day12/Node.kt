package day12

data class Node(val id: Int) {
    val neighbours: MutableList<Node> = mutableListOf()
}