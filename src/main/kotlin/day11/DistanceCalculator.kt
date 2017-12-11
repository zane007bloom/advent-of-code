package day11

class DistanceCalculator {
    fun calculate(directions: List<String>): Map<String, Int> {
        val directionCounts = mutableMapOf<String, Int>()
        var max = 0
        for (direction in directions) {
            val oppositeDirection = getOpposite(direction)
            if (directionCounts.containsKey(oppositeDirection)) {
                decrementDirectionCount(directionCounts, oppositeDirection)
            } else if (canReduce(directionCounts, direction)) {
                reduce(directionCounts, direction)
                var directionToReduce = directionToReduce(directionCounts)
                while (directionToReduce != "") {
                    reduce(directionCounts, directionToReduce)
                    directionToReduce = directionToReduce(directionCounts)
                }
            } else {
                incrementDirectionCount(directionCounts, direction)
            }
            var sum = 0
            for (value in directionCounts.values) {
                sum += value
            }
            max = Math.max(max, sum)
        }
        println("MAX: " + max)
        return directionCounts
    }

    private fun incrementDirectionCount(directionCounts: MutableMap<String, Int>, direction: String) {
        directionCounts.put(direction, directionCounts.getOrDefault(direction, 0) + 1)
    }

    private fun reduce(directionCounts: MutableMap<String, Int>, direction: String) {
        when (direction) {
            "n" -> {
                if (directionCounts.containsKey("sw")) {
                    decrementDirectionCount(directionCounts, "sw")
                    incrementDirectionCount(directionCounts, "nw")
                } else if (directionCounts.containsKey("se")) {
                    decrementDirectionCount(directionCounts, "se")
                    incrementDirectionCount(directionCounts, "ne")
                }
            }
            "s" -> {
                if (directionCounts.containsKey("nw")) {
                    decrementDirectionCount(directionCounts, "nw")
                    incrementDirectionCount(directionCounts, "sw")
                } else if (directionCounts.containsKey("ne")) {
                    decrementDirectionCount(directionCounts, "ne")
                    incrementDirectionCount(directionCounts, "se")
                }
            }
            "ne" -> {
                if (directionCounts.containsKey("nw")) {
                    decrementDirectionCount(directionCounts, "nw")
                    incrementDirectionCount(directionCounts, "n")
                } else if (directionCounts.containsKey("s")) {
                    decrementDirectionCount(directionCounts, "s")
                    incrementDirectionCount(directionCounts, "se")
                }
            }
            "se" -> {
                if (directionCounts.containsKey("sw")) {
                    decrementDirectionCount(directionCounts, "sw")
                    incrementDirectionCount(directionCounts, "s")
                } else if (directionCounts.containsKey("n")) {
                    decrementDirectionCount(directionCounts, "n")
                    incrementDirectionCount(directionCounts, "ne")
                }
            }
            "nw" -> {
                if (directionCounts.containsKey("ne")) {
                    decrementDirectionCount(directionCounts, "ne")
                    incrementDirectionCount(directionCounts, "n")
                } else if (directionCounts.containsKey("s")) {
                    decrementDirectionCount(directionCounts, "s")
                    incrementDirectionCount(directionCounts, "sw")
                }
            }
            "sw" -> {
                if (directionCounts.containsKey("se")) {
                    decrementDirectionCount(directionCounts, "se")
                    incrementDirectionCount(directionCounts, "s")
                } else if (directionCounts.containsKey("n")) {
                    decrementDirectionCount(directionCounts, "n")
                    incrementDirectionCount(directionCounts, "nw")
                }
            }
            else -> throw IllegalStateException("Unknown Direction: " + direction)
        }
    }

    private fun decrementDirectionCount(directionCounts: MutableMap<String, Int>, direction: String) {
        val newCount = directionCounts.getValue(direction) - 1
        if (newCount == 0) {
            directionCounts.remove(direction)
        } else {
            directionCounts.put(direction, newCount)
        }
    }

    private fun canReduce(directionCounts: MutableMap<String, Int>, direction: String): Boolean {
        when (direction) {
            "n" -> return directionCounts.containsKey("sw") || directionCounts.containsKey("se")
            "s" -> return directionCounts.containsKey("nw") || directionCounts.containsKey("ne")
            "ne" -> return directionCounts.containsKey("nw") || directionCounts.containsKey("s")
            "se" -> return directionCounts.containsKey("sw") || directionCounts.containsKey("n")
            "nw" -> return directionCounts.containsKey("ne") || directionCounts.containsKey("s")
            "sw" -> return directionCounts.containsKey("se") || directionCounts.containsKey("n")
            else -> throw IllegalStateException("Unknown Direction: " + direction)
        }
    }

    private fun directionToReduce(directionCounts: MutableMap<String, Int>): String {
        for (direction in directionCounts.keys) {
            if (canReduce(directionCounts, direction)) return direction
        }
        return ""
    }

    private fun getOpposite(direction: String): String {
        return when (direction) {
            "n" -> "s"
            "s" -> "n"
            "ne" -> "sw"
            "sw" -> "ne"
            "nw" -> "se"
            "se" -> "nw"
            else -> throw IllegalStateException("Unknown Direction: " + direction)
        }
    }
}