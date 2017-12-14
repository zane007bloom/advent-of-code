package day14

import day10.KnotTyingHashEncoder
import day3.Point

class Disk {

    fun calculateUsedSpace(input: String): Int {
        var sum = 0
        for (i in 0..127) {
            val binary = encodeToBinary(input + "-$i")
            sum += binary.count { it == '1' }
        }
        return sum
    }

    fun calculateRegions(input: String): Int {
        var currentRegion = 1
        val disk = createDisk(input)
        val toProcess = mutableListOf<Point>()
        val regions = mutableMapOf<Int, MutableList<Point>>()
        regions.put(currentRegion, mutableListOf())
        for (i in 0..127) {
            for (j in 0..127) {
                toProcess.add(Point(j, i))
            }
        }

        while (toProcess.isNotEmpty()) {
            val point = toProcess.removeAt(0)
            if (disk[point.y][point.x] == '1') {
                processPoint(point, disk, regions, currentRegion, toProcess)
                regions.put(++currentRegion, mutableListOf())
            }
        }
        return regions.keys.max()!! - 1
    }

    private fun processPoint(point: Point, disk: List<String>, regions: MutableMap<Int, MutableList<Point>>, currentRegion: Int, toProcess: MutableList<Point>) {
        regions.getValue(currentRegion).add(point)
        val filledNeighbours = point.filledNeighbours(disk)
        for (filledNeighbour in filledNeighbours) {
            if (toProcess.contains(filledNeighbour)) {
                toProcess.remove(filledNeighbour)
                processPoint(filledNeighbour, disk, regions, currentRegion, toProcess)
            }
        }
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