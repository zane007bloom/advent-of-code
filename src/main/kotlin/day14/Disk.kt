package day14

import day10.KnotTyingHashEncoder

class Disk {

    fun calculateUsedSpace(input: String): Int {
        var sum = 0
        for (i in 0..127) {
            val binary = encodeToBinary(input + "-$i")
            println(binary.map { if (it == '1') '#' else "." })
            sum += binary.count { it == '1' }
        }
        return sum
    }

    fun calculateRegions(input: String): Int {
        var sum = 0
        val disk = createDisk(input)
        for (i in 0..127) {

        }
        return sum
    }

    private fun createDisk(input: String): List<String> {
        val disk: MutableList<String> = mutableListOf()
        for (i in 0..127) {
            val binary = encodeToBinary(input + "-$i")
            disk.add(binary)
        }
        return disk
    }

    fun encodeToBinary(s: String): String {
        val lengths = s.toList().map { it.toInt() }.toMutableList()
        lengths.addAll(listOf(17, 31, 73, 47, 23))
        val hexEncoded = KnotTyingHashEncoder().encode(lengths)
        return convertToBinary(hexEncoded)
    }

    fun convertToBinary(hexEncoded: String): String {
        var binaryResult = ""
        for (c in hexEncoded) {
            binaryResult += binaryString(c.toString())
        }
        return binaryResult
    }

    fun binaryString(hexString: String): String {
        val binaryString = Integer.toBinaryString(Integer.parseInt(hexString, 16))
        val zeroesToAdd = 4 - binaryString.length

        return "0".repeat(zeroesToAdd) + binaryString
    }

}