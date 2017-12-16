package day16

import java.io.File

fun main(args: Array<String>) {
    val operations = File("C:/Development/advent-of-code/src/main/kotlin/input.txt").readText().split(",").map { createOperation(it) }
    val result = DanceMoveCalculator().calculateNTimes(operations, 1000000000L)
    println()
    for (c in result) {
        print(c)
    }
    println()
}

fun createOperation(stringOperation: String): Operation {
    return when (stringOperation.first()) {
        's' -> SpinOperation(stringOperation.substring(1).toInt())
        'x' -> {
            val split = stringOperation.substring(1).split("/")
            ExchangeOperation(split[0].toInt(), split[1].toInt())
        }
        'p' -> PartnerOperation(stringOperation[1], stringOperation[3])
        else -> throw RuntimeException("Unknown Operation $stringOperation")
    }
}
