package day16

class SpinOperation(val size: Int) : Operation {

    override fun operate(programs: MutableList<Char>) {
        for (i in 1..size) {
            programs.add(0, programs.removeAt(programs.lastIndex))
        }
    }
}