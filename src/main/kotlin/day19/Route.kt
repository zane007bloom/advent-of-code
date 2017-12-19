package day19

import day3.Direction
import day3.Point

class Route {
    fun traverse(lines: List<String>): String {
        val startingX = lines[0].indexOf('|')
        var currentPoint = Point(startingX, 0)
        var direction = Direction.DOWN
        var visited = ""
        while (!lines[currentPoint.y][currentPoint.x].isWhitespace()) {
            val currentChar = lines[currentPoint.y][currentPoint.x]
            when (currentChar) {
                '|' -> {
                    currentPoint = currentPoint.nextPointInverseY(direction)
                }
                '-' -> {
                    currentPoint = currentPoint.nextPointInverseY(direction)
                }
                in 'A'..'z' -> {
                    visited += currentChar
                    currentPoint = currentPoint.nextPointInverseY(direction)
                }
                '+' -> {
                    direction = changeDirection(lines, currentPoint, direction)
                    currentPoint = currentPoint.nextPointInverseY(direction)
                }
            }

        }
        return visited
    }

    fun traverseCountSteps(lines: List<String>): Int {
        val startingX = lines[0].indexOf('|')
        var currentPoint = Point(startingX, 0)
        var direction = Direction.DOWN
        var visited = ""
        var sum = 0
        while (!lines[currentPoint.y][currentPoint.x].isWhitespace()) {
            val currentChar = lines[currentPoint.y][currentPoint.x]
            when (currentChar) {
                '|' -> {
                    currentPoint = currentPoint.nextPointInverseY(direction)
                    sum++
                }
                '-' -> {
                    currentPoint = currentPoint.nextPointInverseY(direction)
                    sum++
                }
                in 'A'..'z' -> {
                    visited += currentChar
                    currentPoint = currentPoint.nextPointInverseY(direction)
                    sum++
                }
                '+' -> {
                    direction = changeDirection(lines, currentPoint, direction)
                    currentPoint = currentPoint.nextPointInverseY(direction)
                    sum++
                }
            }

        }
        return sum
    }

    private fun changeDirection(lines: List<String>, currentPoint: Point, direction: Direction): Direction {
        val up = currentPoint.nextPointInverseY(Direction.UP)
        val down = currentPoint.nextPointInverseY(Direction.DOWN)
        val left = currentPoint.nextPointInverseY(Direction.LEFT)
        val right = currentPoint.nextPointInverseY(Direction.RIGHT)

        return when (direction) {
            Direction.UP -> {
                if (left.y < lines.size && left.x < lines[left.y].length && !lines[left.y][left.x].isWhitespace()) return Direction.LEFT
                if (up.y < lines.size && up.x < lines[up.y].length && !lines[up.y][up.x].isWhitespace()) return Direction.UP
                if (right.y < lines.size && right.x < lines[right.y].length && !lines[right.y][right.x].isWhitespace()) return Direction.RIGHT
                throw RuntimeException("HUH???")
            }
            Direction.DOWN -> {
                if (left.y < lines.size && left.x < lines[left.y].length && !lines[left.y][left.x].isWhitespace()) return Direction.LEFT
                if (down.y < lines.size && down.x < lines[down.y].length && !lines[down.y][down.x].isWhitespace()) return Direction.DOWN
                if (right.y < lines.size && right.x < lines[right.y].length && !lines[right.y][right.x].isWhitespace()) return Direction.RIGHT
                throw RuntimeException("HUH???")
            }
            Direction.LEFT -> {
                if (left.y < lines.size && left.x < lines[left.y].length && !lines[left.y][left.x].isWhitespace()) return Direction.LEFT
                if (down.y < lines.size && down.x < lines[down.y].length && !lines[down.y][down.x].isWhitespace()) return Direction.DOWN
                if (up.y < lines.size && up.x < lines[up.y].length && !lines[up.y][up.x].isWhitespace()) return Direction.UP
                throw RuntimeException("HUH???")
            }
            Direction.RIGHT -> {
                if (up.y < lines.size && up.x < lines[up.y].length && !lines[up.y][up.x].isWhitespace()) return Direction.UP
                if (down.y < lines.size && down.x < lines[down.y].length && !lines[down.y][down.x].isWhitespace()) return Direction.DOWN
                if (right.y < lines.size && right.x < lines[right.y].length && !lines[right.y][right.x].isWhitespace()) return Direction.RIGHT
                throw RuntimeException("HUH???")
            }
        }
    }

    private fun terminate(nextPoint: Point, lines: List<String>): Boolean {
        return lines[nextPoint.y][nextPoint.x].isWhitespace()
    }
}