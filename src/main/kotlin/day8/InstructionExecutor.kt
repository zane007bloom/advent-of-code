package day8

class InstructionExecutor {

    fun execute(instructions: List<Instruction>): Int {
        val registers: MutableMap<String, Int> = mutableMapOf()
        for (instruction in instructions) {
            if (!registers.containsKey(instruction.register)) registers.put(instruction.register, 0)
            if (!registers.containsKey(instruction.conditionRegister)) registers.put(instruction.conditionRegister, 0)

            if (evaluateCondition(instruction, registers)) {
                if (instruction.operation == Operation.INCREMENT) {
                    val newValue = registers.getValue(instruction.register) + instruction.value
                    registers.put(instruction.register, newValue)
                } else {
                    val newValue = registers.getValue(instruction.register) - instruction.value
                    registers.put(instruction.register, newValue)
                }
            }
        }
        return registers.values.max()!!
    }

    fun executeBiggestEver(instructions: List<Instruction>): Int {
        val registers: MutableMap<String, Int> = mutableMapOf()
        var max = Int.MIN_VALUE
        for (instruction in instructions) {
            if (!registers.containsKey(instruction.register)) registers.put(instruction.register, 0)
            if (!registers.containsKey(instruction.conditionRegister)) registers.put(instruction.conditionRegister, 0)

            if (evaluateCondition(instruction, registers)) {
                if (instruction.operation == Operation.INCREMENT) {
                    val newValue = registers.getValue(instruction.register) + instruction.value
                    registers.put(instruction.register, newValue)
                } else {
                    val newValue = registers.getValue(instruction.register) - instruction.value
                    registers.put(instruction.register, newValue)
                }
            }
            val newMax = registers.values.max()!!
            if (newMax > max) {
                max = newMax
            }
        }
        return max
    }

    private fun evaluateCondition(instruction: Instruction, registers: MutableMap<String, Int>): Boolean {
        val registerValue = registers.getValue(instruction.conditionRegister)
        return when (instruction.conditionOperation) {
            Operation.EQUALS -> registerValue == instruction.conditionValue
            Operation.NOT_EQUALS -> registerValue != instruction.conditionValue
            Operation.GT -> registerValue > instruction.conditionValue
            Operation.LT -> registerValue < instruction.conditionValue
            Operation.GTE -> registerValue >= instruction.conditionValue
            else -> registerValue <= instruction.conditionValue
        }
    }

}