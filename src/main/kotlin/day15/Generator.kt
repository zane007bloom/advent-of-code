package day15

class Generator {

    fun calculateNumberOfMatches(genAStartingValue: Long, genBStartingValue: Long): Int {
        var genAValue = genAStartingValue
        var genBValue = genBStartingValue

        val genAFactor = 16807L
        val genBFactor = 48271L

        var sum = 0

        for (i in 1..40000000) {
            genAValue = (genAValue * genAFactor).rem(2147483647L)
            genBValue = (genBValue * genBFactor).rem(2147483647L)

            val genABinary = BinaryConverter.convert(genAValue).reversed()
            val genABinaryFirst = genABinary.substring(0, minOf(16, genABinary.length))
            val genBBinary = BinaryConverter.convert(genBValue).reversed()
            val genBBinaryFirst = genBBinary.substring(0, minOf(16, genBBinary.length))

            if (genABinaryFirst == genBBinaryFirst) sum++
        }

        return sum
    }

    fun calculateNumberOfMatchesMultiples(genAStartingValue: Long, genBStartingValue: Long): Int {
        var genAValue = genAStartingValue
        var genBValue = genBStartingValue

        val genAFactor = 16807L
        val genBFactor = 48271L

        val genAValues = mutableListOf<String>()
        val genANumValues = mutableListOf<Long>()
        val genBValues = mutableListOf<String>()
        val genBNumValues = mutableListOf<Long>()

        var sum = 0

        val numberOfPairs = 5000000
        while (genAValues.size < numberOfPairs || genBValues.size < numberOfPairs) {
            genBValue = (genBValue * genBFactor).rem(2147483647L)

            if (genAValues.size < numberOfPairs) {
                genAValue = (genAValue * genAFactor).rem(2147483647L)
                if (genAValue.rem(4L) == 0L) {
                    val genABinary = BinaryConverter.convert(genAValue).reversed()
                    val genABinaryFirst = genABinary.substring(0, minOf(16, genABinary.length))
                    genAValues.add(genABinaryFirst)
                    genANumValues.add(genAValue)
                }
            }
            if (genBValues.size < numberOfPairs) {
                if (genBValue.rem(8L) == 0L) {
                    val genBBinary = BinaryConverter.convert(genBValue).reversed()
                    val genBBinaryFirst = genBBinary.substring(0, minOf(16, genBBinary.length))
                    genBValues.add(genBBinaryFirst)
                    genBNumValues.add(genBValue)
                }
            }
        }

        for (i in 0 until numberOfPairs) {
            if (genAValues[i] == genBValues[i]) sum++
        }

        return sum
    }

}