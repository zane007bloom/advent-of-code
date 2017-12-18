package day18

class ReceiveOperation(val register: String) : Operation {
    override fun execute(state: State): Boolean {
        if (state.messages.isEmpty()) {
            return false
        }
        state.registers.put(register, state.messages.removeAt(0))
        state.currentIndex++
        return true
    }
}