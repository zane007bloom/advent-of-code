package day3

class DistanceCalculator {

    fun calculate(fromNumber: Double): Int {
        if (fromNumber == 1.0) {
            return 0
        }
        val sqrt = Math.sqrt(fromNumber)
        if (sqrt == Math.floor(sqrt) && sqrt % 2 == 1.0) return sqrt.toInt() + sqrt.toInt()
        var flooredSqrt = Math.floor(sqrt).toInt()
        if (flooredSqrt % 2 == 1) {
            flooredSqrt += 2
        } else {
            flooredSqrt++
        }
        val row = (flooredSqrt - 1) / 2
        val count = flooredSqrt * flooredSqrt
        var fromX = row
        var fromY = row
        var direction = Direction.LEFT
        val corners = listOf(count - ((flooredSqrt - 1) * 1), count - ((flooredSqrt - 1) * 2), count - ((flooredSqrt - 1) * 3))
        for (i in count - 1 downTo fromNumber.toInt()) {
            when (direction) {
                Direction.LEFT -> fromX--
                Direction.UP -> fromY--
                Direction.RIGHT -> fromX++
                else -> fromY++
            }
            if (corners.contains(i)) {
                direction = when (direction) {
                    Direction.LEFT -> Direction.UP
                    Direction.UP -> Direction.RIGHT
                    else -> Direction.DOWN
                }
            }
        }
        return Math.abs(fromX) + Math.abs(fromY)
    }

    fun calculateSum(toNumber: Double): Double {
        val numbers: MutableMap<Point, Double> = mutableMapOf()
        var lastPoint = Point(0, 0)
        numbers.put(lastPoint, 1.0)

        var lastSum = 0.0
        var currentDirection = Direction.RIGHT

        while (lastSum < toNumber) {
            currentDirection = nextDirection(lastPoint, currentDirection)
            val nextPoint: Point = lastPoint.nextPoint(currentDirection)
            lastSum = calculateNeighboursSum(numbers, nextPoint)
            numbers.put(nextPoint, lastSum)
            lastPoint = nextPoint
            println(lastSum)
        }

        return lastSum
    }

    private fun calculateNeighboursSum(numbers: Map<Point, Double>, point: Point): Double {
        var sum = 0.0
        sum += numbers.getOrDefault(Point(point.x+1, point.y), 0.0)

        sum += numbers.getOrDefault(Point(point.x+1, point.y+1), 0.0)
        sum += numbers.getOrDefault(Point(point.x, point.y+1), 0.0)
        sum += numbers.getOrDefault(Point(point.x-1, point.y+1), 0.0)

        sum += numbers.getOrDefault(Point(point.x-1, point.y), 0.0)

        sum += numbers.getOrDefault(Point(point.x-1, point.y-1), 0.0)
        sum += numbers.getOrDefault(Point(point.x, point.y-1), 0.0)
        sum += numbers.getOrDefault(Point(point.x+1, point.y-1), 0.0)
        return sum
    }

    private fun nextDirection(lastPoint: Point, currentDirection: Direction): Direction {
        if (lastPoint == Point(0, 0)) return Direction.RIGHT
        if (-(lastPoint.x - 1) == lastPoint.y && lastPoint.x > lastPoint.y) return Direction.UP
        if (lastPoint.x > 0 && lastPoint.y > 0 && lastPoint.x == lastPoint.y) return Direction.LEFT
        if (lastPoint.x < 0 && lastPoint.y > 0 && -lastPoint.x == lastPoint.y) return Direction.DOWN
        if (lastPoint.x < 0 && lastPoint.y < 0 && lastPoint.x == lastPoint.y) return Direction.RIGHT
        return currentDirection
    }

}