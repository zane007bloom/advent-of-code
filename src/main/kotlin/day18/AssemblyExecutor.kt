package day18

class AssemblyExecutor {

    fun execute(state: State): Long {
        while (state.currentIndex in 0 until state.operations.size && !state.registers.containsKey(SpecialRegisters.RECOVERED.name)) {
            val operation = state.operations[state.currentIndex]
            operation.execute(state)
        }
        return state.registers.getValue(SpecialRegisters.RECOVERED.name)
    }

    fun executeBetweenProcesses(state: State): Boolean {
        do {
            val operation = state.operations[state.currentIndex]
            val success = operation.execute(state)
        } while (success)
        return false
    }

}
