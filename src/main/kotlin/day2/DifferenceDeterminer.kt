package day2

class DifferenceDeterminer {

    fun determine(numbers: List<Int>): Int {
        var biggest = numbers.first()
        var smallest = numbers.first()

        for (number in numbers) {
            if (number > biggest) biggest = number
            if (number < smallest) smallest = number
        }

        return biggest - smallest
    }

    fun determineModulus(numbers: List<Int>): Int {
        var biggest = numbers.first()
        var smallest = numbers.first()

        for (i in 0 until numbers.size) {
            for (j in i+1 until numbers.size) {
                if (numbers[i] == numbers[j]) {
                    return 1
                } else if (numbers[i] > numbers[j]) {
                    if (numbers[i] % numbers[j] == 0) {
                        return numbers[i] / numbers[j]
                    }
                } else {
                    if (numbers[j] % numbers[i] == 0) {
                        return numbers[j] / numbers[i]
                    }
                }
            }
        }

        throw IllegalArgumentException("Huh???")
    }

}