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