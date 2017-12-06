package day5

class EscapeHelper {

    fun escape(jumps: MutableList<Int>): Int {
        var currentIndex = 0
        var jumpsSum = 0
        while (currentIndex < jumps.size) {
            val oldIndex = currentIndex
            currentIndex += jumps[currentIndex]
            if (oldIndex < jumps.size) jumps[oldIndex] = jumps[oldIndex] + 1
            jumpsSum++
        }
        return jumpsSum
    }

    fun escapeNewRules(jumps: MutableList<Int>): Int {
        var currentIndex = 0
        var jumpsSum = 0
        while (currentIndex < jumps.size) {
            val oldIndex = currentIndex
            currentIndex += jumps[currentIndex]
            if (oldIndex < jumps.size)
                if (jumps[oldIndex] >= 3)
                    jumps[oldIndex] = jumps[oldIndex] - 1
                else jumps[oldIndex] = jumps[oldIndex] + 1
            jumpsSum++
        }
        return jumpsSum
    }

}