package day18

import java.io.File

fun main(args: Array<String>) {
    part1()
    part2()
}

private fun part2() {
    val assemblyExecutor = AssemblyExecutor()
    val operations = File("C:/Development/advent-of-code/src/main/kotlin/input.txt").readLines().map { createOperationPart2(it.split(Regex("\\s+"))) }

    val p0Messages = mutableListOf<Long>()
    val p1Messages = mutableListOf<Long>()

    val p0Registers = mutableMapOf<String, Long>()
    for (c in 'a'..'z') {
        p0Registers.put(c.toString(), 0)
    }
    p0Registers.put("p", 0)
    p0Registers.put(SpecialRegisters.SENT_COUNT.name, 0)
    val p0State = State(operations, 0, p0Registers, p0Messages, p1Messages)

    val p1Registers = mutableMapOf<String, Long>()
    for (c in 'a'..'z') {
        p1Registers.put(c.toString(), 0)
    }
    p1Registers.put("p", 1)
    p1Registers.put(SpecialRegisters.SENT_COUNT.name, 0)
    val p1State = State(operations, 0, p1Registers, p1Messages, p0Messages)

    while (!terminate(p0State, p1State)) {
        assemblyExecutor.executeBetweenProcesses(p0State)
        assemblyExecutor.executeBetweenProcesses(p1State)
    }

    println()

    print(p1State.registers.getValue(SpecialRegisters.SENT_COUNT.name))

    println()
}

private fun terminate(p0State: State, p1State: State): Boolean {
    return p0State.operations[p0State.currentIndex] is ReceiveOperation && p0State.messages.isEmpty()
            && p1State.operations[p1State.currentIndex] is ReceiveOperation && p1State.messages.isEmpty()
}

private fun part1() {
    val operations = File("C:/Development/advent-of-code/src/main/kotlin/input.txt").readLines().map { createOperationPart1(it.split(Regex("\\s+"))) }

    println()
    val registers = mutableMapOf<String, Long>()
    for (c in 'a'..'z') {
        registers.put(c.toString(), 0)
    }
    print(AssemblyExecutor().execute(State(operations, 0, registers, mutableListOf(), mutableListOf())))
    println()
}

fun createOperationPart2(stringOperation: List<String>): Operation {
    return when (stringOperation[0]) {
        "snd" -> SendOperation(stringOperation[1])
        "set" -> SetOperation(stringOperation[1], stringOperation[2])
        "add" -> AddOperation(stringOperation[1], stringOperation[2])
        "mul" -> MultiplyOperation(stringOperation[1], stringOperation[2])
        "mod" -> ModOperation(stringOperation[1], stringOperation[2])
        "rcv" -> ReceiveOperation(stringOperation[1])
        "jgz" -> JumpOperation(stringOperation[1], stringOperation[2])
        else -> throw RuntimeException("Unknown Operation $stringOperation")
    }
}

fun createOperationPart1(stringOperation: List<String>): Operation {
    return when (stringOperation[0]) {
        "snd" -> PlayOperation(stringOperation[1])
        "set" -> SetOperation(stringOperation[1], stringOperation[2])
        "add" -> AddOperation(stringOperation[1], stringOperation[2])
        "mul" -> MultiplyOperation(stringOperation[1], stringOperation[2])
        "mod" -> ModOperation(stringOperation[1], stringOperation[2])
        "rcv" -> RecoverOperation(stringOperation[1])
        "jgz" -> JumpOperation(stringOperation[1], stringOperation[2])
        else -> throw RuntimeException("Unknown Operation $stringOperation")
    }
}