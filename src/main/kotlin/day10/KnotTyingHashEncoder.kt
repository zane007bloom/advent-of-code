package day10

class KnotTyingHashEncoder {

    var skipSize = 0
    var currentIndex = 0
    val sparseHash = MutableCircularList<Int>()

    fun runRound(lengths: List<Int>): MutableCircularList<Int> {
        for (length in lengths) {
            val subList = sparseHash.subList(currentIndex, currentIndex + length - 1)
            sparseHash.replaceAll(currentIndex, subList.reversed())
            currentIndex += length + skipSize
            skipSize++
        }
        return sparseHash
    }

    fun encode(lengths: List<Int>): String {
        for (i in 0 until 64) {
            runRound(lengths)
        }
        val denseHash: MutableList<Int> = mutableListOf()
        for (i in 0 until 16) {
            var xorResult = sparseHash[i * 16]
            for (j in (i * 16 + 1) until (i * 16 + 16)) {
                xorResult = xorResult.xor(sparseHash[j])
            }
            denseHash.add(xorResult)
        }
        var result = ""
        for (denseHashEl in denseHash) {
            result += if (denseHashEl < 16) "0" + Integer.toHexString(denseHashEl) else Integer.toHexString(denseHashEl)
        }
        return result
    }

    init {
        sparseHash.addAll(0..255)
    }

}