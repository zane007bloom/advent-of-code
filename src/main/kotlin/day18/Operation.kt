package day18

interface Operation {
    fun execute(state: State): Boolean

    fun determineValue(value: String, registers: MutableMap<String, Long>): Long {
        return if (value.toIntOrNull() == null) {
            registers.getValue(value)
        } else {
            value.toLong()
        }
    }
}