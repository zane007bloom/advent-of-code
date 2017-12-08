package day8

import java.io.File

fun main(args: Array<String>) {
    val fileLines = File("/home/zane/IdeaProjects/advent-of-code/src/main/kotlin/input.txt").readLines()
    val instructions: List<Instruction> = fileLines.map { line -> createInstruction(line) }
    print(InstructionExecutor().executeBiggestEver(instructions))
}

fun createInstruction(line: String): Instruction {
    val words = line.split(" ", "if").filter { it != "" }
    return Instruction(words[0], determine(words[1]), words[2].toInt(), words[3], determine(words[4]), words[5].toInt())
}

fun determine(value: String): Operation {
    return when (value) {
        "inc" -> Operation.INCREMENT
        "dec" -> Operation.DECREMENT
        ">" -> Operation.GT
        "<" -> Operation.LT
        ">=" -> Operation.GTE
        "<=" -> Operation.LTE
        "!=" -> Operation.NOT_EQUALS
        else -> Operation.EQUALS
    }
}
