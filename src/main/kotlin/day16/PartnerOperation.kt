package day16

class PartnerOperation(private val program1: Char, private val program2: Char) : Operation {
    override fun operate(programs: MutableList<Char>) {
        val pos1 = programs.indexOf(program1)
        val pos2 = programs.indexOf(program2)
        ExchangeOperation(pos1, pos2).operate(programs)
    }
}