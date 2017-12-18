package day18

class SetOperation(private val register: String, private val value: String) : Operation {
    override fun execute(state: State): Boolean {
        state.registers.put(register, determineValue(value, state.registers))
        state.currentIndex++
        return true
    }
}