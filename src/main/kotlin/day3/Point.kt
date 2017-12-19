package day3

data class Point(val x:Int, val y: Int) {
    fun nextPoint(direction: Direction): Point {
        return when (direction) {
            Direction.UP -> Point(x, y+1)
            Direction.LEFT -> Point(x-1, y)
            Direction.DOWN -> Point(x, y-1)
            Direction.RIGHT -> Point(x+1, y)
        }
    }

    fun nextPointInverseY(direction: Direction): Point {
        return when (direction) {
            Direction.UP -> Point(x, y - 1)
            Direction.LEFT -> Point(x - 1, y)
            Direction.DOWN -> Point(x, y + 1)
            Direction.RIGHT -> Point(x + 1, y)
        }
    }

    fun isValid(): Boolean {
        return x >= 0 && y >= 0 && x <= 127 && y <= 127
    }

    fun filledNeighbours(disk: List<String>): List<Point> {
        val neighbours = mutableListOf<Point>()

        val up = Point(x, y + 1)
        val down = Point(x, y - 1)
        val left = Point(x + 1, y)
        val right = Point(x - 1, y)

        if (up.isValid() && disk[up.y][up.x] == '1') neighbours.add(up)
        if (down.isValid() && disk[down.y][down.x] == '1') neighbours.add(down)
        if (left.isValid() && disk[left.y][left.x] == '1') neighbours.add(left)
        if (right.isValid() && disk[right.y][right.x] == '1') neighbours.add(right)

        return neighbours
    }
}