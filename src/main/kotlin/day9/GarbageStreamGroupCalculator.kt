package day9

class GarbageStreamGroupCalculator {
    fun calculateNumberOfGroups(input: String): Int {
        var sum = 0

        var level = 0
        var isJunk = false
        var index = 0

        while (index < input.length) {
            val char = input[index]
            if (!isJunk && char == '<') {
                isJunk = true
            } else if (char == '>') {
                isJunk = false
            } else if (char == '!') {
                index++
            } else if (!isJunk && char == '{') {
                level++
                sum += level
            } else if (!isJunk && char == '}') {
                level--
            }
            index++
        }

        return sum
    }

    fun calculateNumberOfRandomChars(input: String): Int {
        var sum = 0

        var level = 0
        var isJunk = false
        var index = 0

        while (index < input.length) {
            val char = input[index]
            if (!isJunk && char == '<') {
                isJunk = true
            } else if (char == '>') {
                isJunk = false
            } else if (char == '!') {
                index++
            } else if (!isJunk && char == '{') {
                level++
            } else if (!isJunk && char == '}') {
                level--
            } else if (isJunk) {
                sum++
            }
            index++
        }

        return sum
    }

}