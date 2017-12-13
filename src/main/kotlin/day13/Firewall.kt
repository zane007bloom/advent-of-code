package day13

class Firewall {
    fun calculateSeverity(layers: MutableMap<Int, Int>): Int {
        var layersCaughtIn = mutableListOf<Int>()
        val layerPositions = mutableMapOf<Int, Int>()
        val layerDirections = mutableMapOf<Int, Direction>()
        for (layer in layers.keys) {
            layerPositions.put(layer, 0)
            layerDirections.put(layer, Direction.DOWN)
        }
        val numberOfLayers = layers.keys.max()
        for (i in 0..numberOfLayers!!) {
            if (layerPositions.containsKey(i) && layerPositions.getValue(i) == 0) {
                layersCaughtIn.add(i)
            }
            updateLayerPositions(layerPositions, layerDirections, layers)
        }
        return calculateSeverity(layersCaughtIn, layers)
    }

    fun calulateDelay(layers: MutableMap<Int, Int>): Int {
        var layersCaughtIn = mutableMapOf<Int, MutableList<Int>>()
        val layerPositions = mutableMapOf<Int, Int>()
        val layerDirections = mutableMapOf<Int, Direction>()
        var picoSecond = 0
        val currentPositions = mutableMapOf<Int, Int>()
        val numberOfLayers = layers.keys.max()

        for (layer in layers.keys) {
            layerPositions.put(layer, 0)
            layerDirections.put(layer, Direction.DOWN)
        }

        while (true) {
            updateCurrentPositions(picoSecond, currentPositions, layersCaughtIn)

            for (currentPosition in currentPositions) {
                if (layerPositions.containsKey(currentPosition.value) && layerPositions.getValue(currentPosition.value) == 0) {
                    layersCaughtIn.getValue(currentPosition.key).add(currentPosition.value)
                }
            }

            updateLayerPositions(layerPositions, layerDirections, layers)
            val toRemove = mutableListOf<Int>()
            for (currentPosition in currentPositions) {
                if (currentPosition.value > numberOfLayers!!) {
                    if (layersCaughtIn.getValue(currentPosition.key).isEmpty()) {
                        return currentPosition.key
                    } else {
                        toRemove.add(currentPosition.key)
                    }
                }
            }
            currentPositions.keys.removeAll(toRemove)
            picoSecond++
        }
        return -1
    }

    private fun updateCurrentPositions(picoSecond: Int, currentPositions: MutableMap<Int, Int>, layersCaughtIn: MutableMap<Int, MutableList<Int>>) {
        for (key in currentPositions.keys) {
            currentPositions.put(key, currentPositions.getValue(key) + 1)
        }
        currentPositions.put(picoSecond, 0)
        layersCaughtIn.put(picoSecond, mutableListOf())
    }

    private fun calculateSeverity(layersCaughtIn: MutableList<Int>, layers: MutableMap<Int, Int>): Int {
        var sum = 0
        for (layerCaughtIn in layersCaughtIn) {
            sum += layerCaughtIn * layers.getValue(layerCaughtIn)
        }
        return sum
    }

    private fun updateLayerPositions(layerPositions: MutableMap<Int, Int>, layerDirections: MutableMap<Int, Direction>, layers: MutableMap<Int, Int>) {
        for (layer in layers) {
            val currentPos = layerPositions.getValue(layer.key)
            var curretDirection = layerDirections.getValue(layer.key)
            if (curretDirection == Direction.DOWN && currentPos == layer.value - 1) curretDirection = Direction.UP
            if (curretDirection == Direction.UP && currentPos == 0) curretDirection = Direction.DOWN
            layerDirections.put(layer.key, curretDirection)
            val newPos = if (curretDirection == Direction.DOWN) currentPos + 1 else currentPos - 1
            layerPositions.put(layer.key, newPos)
        }
    }
}

enum class Direction {
    UP, DOWN
}