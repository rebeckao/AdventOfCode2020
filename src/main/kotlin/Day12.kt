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

    private fun turnLeft(currentDirection: Char, amount: Int): Char {
        var newDir = currentDirection
        for (i in 0 until amount / 90) {
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

    private fun turnRight(currentDirection: Char, amount: Int): Char {
        var newDir = currentDirection
        for (i in 0 until amount / 90) {
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
}