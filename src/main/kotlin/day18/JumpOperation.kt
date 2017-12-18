package day18

class JumpOperation(private val register: String, private val offset: String) : Operation {
    override fun execute(state: State): Boolean {
        if (determineValue(register, state.registers) > 0) {
            state.currentIndex += determineValue(offset, state.registers).toInt()
        } else {
            state.currentIndex++
        }
        return true
    }
}