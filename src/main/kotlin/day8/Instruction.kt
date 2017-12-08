package day8

data class Instruction(
        val register: String,
        val operation: Operation,
        val value: Int,
        val conditionRegister: String,
        val conditionOperation: Operation,
        val conditionValue: Int)

enum class Operation {
    INCREMENT,
    DECREMENT,
    EQUALS,
    NOT_EQUALS,
    GT,
    LT,
    GTE,
    LTE;
}