package day18

class RecoverOperation(private val register: String) : Operation {
    override fun execute(state: State): Boolean {
        if (determineValue(register, state.registers) != 0L) {
            val lastPlayed = state.registers.getValue(SpecialRegisters.LAST_PLAYED.name)
            state.registers.put(SpecialRegisters.RECOVERED.name, lastPlayed)
        }
        state.currentIndex++
        return true
    }
}