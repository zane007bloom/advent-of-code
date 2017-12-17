package day17

import day10.MutableCircularList

class SpinLock {

    fun shortCircuit(stepSize: Int): Int {
        val values = MutableCircularList<Int>()
        values.add(0)
        var currentPos = 0
        for (i in 1..2017) {
            val size = values.size
            currentPos = calculateIndex(currentPos + stepSize, size)
            values.addAfter(currentPos++, i)
        }
        return values[calculateIndex(values.indexOf(2017) + 1, values.size)]
    }

    fun angryShortCircuit(stepSize: Int): Int {
        var size = 1
        var currentPos = 0
        var lastValueAtFirstIndex = 1
        for (i in 1..50000000) {
            currentPos = calculateIndex(currentPos + stepSize, size) + 1
            if (currentPos == 1) {
                lastValueAtFirstIndex = i
            }
            size++
        }
        return lastValueAtFirstIndex
    }

    private fun calculateIndex(index: Int, size: Int): Int {
        val factor = index / size
        return index - (factor * size)
    }

}