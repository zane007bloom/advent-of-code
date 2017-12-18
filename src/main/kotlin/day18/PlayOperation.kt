package day18

class PlayOperation(private val register: String) : Operation {

    override fun execute(state: State): Boolean {
        state.registers.put(SpecialRegisters.LAST_PLAYED.name, determineValue(register, state.registers))
        state.currentIndex++
        return true
    }
}