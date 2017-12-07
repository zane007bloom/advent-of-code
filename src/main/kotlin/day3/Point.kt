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
}