package day18

data class State(val operations: List<Operation>, var currentIndex: Int = 0, val registers: MutableMap<String, Long>, val messages: MutableList<Long>, val otherProcessMessages: MutableList<Long>)