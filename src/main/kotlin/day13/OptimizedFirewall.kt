package day13

import day10.MutableCircularList

class OptimizedFirewall {

    fun calculateDelay(layers: MutableMap<Int, Int>): Int {
        val layerFunctions = mutableMapOf<Int, MutableCircularList<Int>>()
        for (layer in layers) {
            val function = MutableCircularList<Int>()
            function.addAll(1..layer.value)
            function.addAll(layer.value - 1 downTo 2)
            layerFunctions.put(layer.key, function)
        }
        var picoSecond = 0
        while (true) {
            var caught = false
            for (layer in layers) {
                val position = getPosition(layerFunctions, layer.key, layer.key + picoSecond - 1)
                if (position == 1) {
                    caught = true
                    break
                }
            }
            if (!caught) return picoSecond - 1
            picoSecond++
        }
        return 0
    }

    private fun getPosition(layerFunctions: MutableMap<Int, MutableCircularList<Int>>, layerNumber: Int, picoSecond: Int): Int {
        if (picoSecond < 0) {
            return 1
        }
        return layerFunctions.getValue(layerNumber)[picoSecond]
    }

}