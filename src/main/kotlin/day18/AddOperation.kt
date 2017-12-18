package day18

class AddOperation(private val register: String, private val value: String) : Operation {
    override fun execute(state: State): Boolean {
        state.registers.put(register, state.registers.getValue(register) + determineValue(value, state.registers))
        state.currentIndex++
        return true
    }
}