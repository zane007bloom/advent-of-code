package day16

class ExchangeOperation(private val pos1: Int, private val pos2: Int) : Operation {
    override fun operate(programs: MutableList<Char>) {
        val program1 = programs[pos1]
        programs[pos1] = programs[pos2]
        programs[pos2] = program1
    }
}