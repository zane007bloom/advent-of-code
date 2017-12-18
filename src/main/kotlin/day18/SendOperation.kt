package day18

class SendOperation(private val register: String) : Operation {
    override fun execute(state: State): Boolean {
        state.otherProcessMessages.add(determineValue(register, state.registers))
        state.registers.put(SpecialRegisters.SENT_COUNT.name, state.registers.getValue(SpecialRegisters.SENT_COUNT.name) + 1)
        state.currentIndex++
        return true
    }
}