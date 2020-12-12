import java.lang.Math.abs

class Day12 {
    fun manhattanDistanceToTarget(instructions: List<String>): Int {
        var x = 0
        var y = 0
        var currentDirection = 'E'
        for (instruction in instructions) {
            val action = instruction[0]
            val amount = instruction.substring(1).toInt()
            when (action) {
                'N' -> y += amount
                'S' -> y -= amount
                'E' -> x += amount
                'W' -> x -= amount
                'L' -> currentDirection = turnLeft(currentDirection, amount)
                'R' -> currentDirection = turnRight(currentDirection, amount)
                'F' -> when (currentDirection) {
                    'N' -> y += amount
                    'S' -> y -= amount
                    'E' -> x += amount
                    'W' -> x -= amount
                }
            }
        }
        return abs(x) + abs(y)
    }

    private fun turnLeft(currentDirection: Char, degrees: Int): Char {
        var newDir = currentDirection
        for (i in 0 until degrees / 90) {
            newDir = when (newDir) {
                'N' -> 'W'
                'S' -> 'E'
                'E' -> 'N'
                'W' -> 'S'
                else -> currentDirection
            }
        }
        return newDir
    }

    private fun turnRight(currentDirection: Char, degrees: Int): Char {
        var newDir = currentDirection
        for (i in 0 until degrees / 90) {
            newDir = when (newDir) {
                'N' -> 'E'
                'S' -> 'W'
                'E' -> 'S'
                'W' -> 'N'
                else -> currentDirection
            }
        }
        return newDir
    }

    fun manhattanDistanceToTargetWithWaypoint(instructions: List<String>): Int {
        var x = 0
        var y = 0
        var xWaypoint = 10
        var yWaypoint = 1
        for (instruction in instructions) {
            val action = instruction[0]
            val amount = instruction.substring(1).toInt()
            when (action) {
                'N' -> yWaypoint += amount
                'S' -> yWaypoint -= amount
                'E' -> xWaypoint += amount
                'W' -> xWaypoint -= amount
                'L' -> {
                    val newWaypoint = rotateWaypointLeft(xWaypoint, yWaypoint, amount)
                    xWaypoint = newWaypoint.first
                    yWaypoint = newWaypoint.second
                }
                'R' -> {
                    val newWaypoint = rotateWaypointRight(xWaypoint, yWaypoint, amount)
                    xWaypoint = newWaypoint.first
                    yWaypoint = newWaypoint.second
                }
                'F' -> {
                    x += amount * xWaypoint
                    y += amount * yWaypoint
                }
            }
        }
        return abs(x) + abs(y)
    }

    private fun rotateWaypointLeft(originalX: Int, originalY: Int, degrees: Int): Pair<Int, Int> {
        var x = originalX
        var y = originalY
        for (i in 0 until degrees / 90) {
            val newX = -y
            val newY = x
            x = newX
            y = newY
        }
        return Pair(x, y)
    }

    private fun rotateWaypointRight(originalX: Int, originalY: Int, degrees: Int): Pair<Int, Int> {
        var x = originalX
        var y = originalY
        for (i in 0 until degrees / 90) {
            val newX = y
            val newY = -x
            x = newX
            y = newY
        }
        return Pair(x, y)
    }
}