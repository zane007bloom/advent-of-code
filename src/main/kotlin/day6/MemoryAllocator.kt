package day6

class MemoryAllocator {

    fun allocate(banks: List<Int>): Int {
        var count = 0

        val previousStates: MutableList<List<Int>> = mutableListOf()
        previousStates.add(banks)

        var currentState = banks

        var stop = false

        while (!stop) {
            val max = currentState.max()
            val maxIndex = currentState.indexOf(max)

            val newState = currentState.toMutableList()

            val totalDistributeAmount = newState[maxIndex]

            val individualDistributionAmount = totalDistributeAmount / newState.size
            val remainder = totalDistributeAmount.rem(newState.size)

            newState[maxIndex] = 0

            if (individualDistributionAmount > 0) {
                for (i in 0 until newState.size) {
                    newState[i] = newState[i] + individualDistributionAmount
                }
            }

            if (remainder > 0) {
                for (i in 1..remainder) {
                    var index = maxIndex + i
                    if (index >= newState.size) index -= newState.size
                    newState[index] = newState[index] + 1
                }
            }

            stop = previousStates.contains(newState)

            previousStates.add(newState)

            currentState = newState

            count++
        }

        return count
    }

    fun allocateFindLastSeenLength(banks: List<Int>): Int {
        var count = 0

        val previousStates: MutableList<List<Int>> = mutableListOf()
        previousStates.add(banks)

        var currentState = banks

        var stop = false

        while (!stop) {
            val max = currentState.max()
            val maxIndex = currentState.indexOf(max)

            val newState = currentState.toMutableList()

            val totalDistributeAmount = newState[maxIndex]

            val individualDistributionAmount = totalDistributeAmount / newState.size
            val remainder = totalDistributeAmount.rem(newState.size)

            newState[maxIndex] = 0

            if (individualDistributionAmount > 0) {
                for (i in 0 until newState.size) {
                    newState[i] = newState[i] + individualDistributionAmount
                }
            }

            if (remainder > 0) {
                for (i in 1..remainder) {
                    var index = maxIndex + i
                    if (index >= newState.size) index -= newState.size
                    newState[index] = newState[index] + 1
                }
            }

            stop = previousStates.contains(newState)

            if (stop) {
                println("Distance apart: " + (previousStates.size - previousStates.indexOf(newState)))
            }

            previousStates.add(newState)

            currentState = newState

            count++
        }

        return count
    }

}