package day1

class CapthaAdder {

    fun add(digits: List<Int>): Int {
        var sum = 0
        for (i in 0 until digits.size) {
            val current = digits[i]
            val next = if (i == digits.size - 1) {
                digits[0]
            } else {
                digits[i + 1]
            }
            if (current == next) {
                sum += current
            }
        }
        return sum
    }

    fun addHalfForward(digits: List<Int>): Int {
        var sum = 0
        val half = digits.size / 2
        for (i in 0 until digits.size) {
            val current = digits[i]
            val next = if (i >= half) {
                digits[i - half]
            } else {
                digits[i + half]
            }
            if (current == next) {
                sum += current
            }
        }
        return sum
    }

}