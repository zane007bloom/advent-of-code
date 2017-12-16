package day16

class DanceMoveCalculator {

    fun calculate(operations: List<Operation>): List<Char> {
        val programs = mutableListOf<Char>()
        programs.addAll('a'..'p')
        for (operation in operations) {
            operation.operate(programs)
        }
        return programs
    }

    fun calculateNTimes(operations: List<Operation>, n: Long): List<Char> {
        val allPossibleValues = determineAllPossibleValues(operations)
        val index = (n - 1).rem(allPossibleValues.size)
        return allPossibleValues[index.toInt()]
    }

    fun determineAllPossibleValues(operations: List<Operation>): List<List<Char>> {
        val programs = mutableListOf<Char>()
        programs.addAll('a'..'p')
        val originalOrder = programs.toList()
        val possibleValues = mutableListOf<List<Char>>()
        do {
            for (operation in operations) {
                operation.operate(programs)
            }
            val currentOrder = programs.toList()
            possibleValues.add(currentOrder)
        } while (currentOrder != originalOrder)
        return possibleValues
    }

}