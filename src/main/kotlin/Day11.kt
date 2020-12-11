class Day11 {
    fun occupiedSeatsAfterConvergence(seats: List<String>): Int {
        var seatMap = seats.map { it.toCharArray() }
        while (true) {
            val nextSeats = mutableListOf<CharArray>()
            for (y in seats.indices) {
                nextSeats.add(seatMap[y].map { it }.toCharArray())
                for (x in seatMap[0].indices) {
                    if (nextSeats[y][x] == '.') {
                        continue
                    }
                    val adjacentSeats = numberOfAdjacentOccupiedSeats(x, y, seatMap)
                    if (adjacentSeats >= 4) {
                        nextSeats[y][x] = 'L'
                    } else if (adjacentSeats == 0) {
                        nextSeats[y][x] = '#'
                    }

                }
            }
            val previousState = seatMap.joinToString { it.joinToString() }
            val newState = nextSeats.joinToString { it.joinToString() }
            if (newState.equals(previousState)) {
                return newState.count { it == '#' }
            }
            seatMap = nextSeats
        }
    }

    private fun numberOfAdjacentOccupiedSeats(
        x: Int,
        y: Int,
        seatMap: List<CharArray>
    ): Int {
        var adjacentSeats = 0
        adjacentSeats += occupiedSeatInDirection(-1, 0, x, y, seatMap)
        adjacentSeats += occupiedSeatInDirection(1, 0, x, y, seatMap)
        adjacentSeats += occupiedSeatInDirection(0, -1, x, y, seatMap)
        adjacentSeats += occupiedSeatInDirection(0, 1, x, y, seatMap)
        adjacentSeats += occupiedSeatInDirection(-1, -1, x, y, seatMap)
        adjacentSeats += occupiedSeatInDirection(1, -1, x, y, seatMap)
        adjacentSeats += occupiedSeatInDirection(-1, 1, x, y, seatMap)
        adjacentSeats += occupiedSeatInDirection(1, 1, x, y, seatMap)
        return adjacentSeats
    }

    private fun occupiedSeatInDirection(xDir: Int, yDir: Int, x: Int, y: Int, seatMap: List<CharArray>): Int {
        val newX = x + xDir
        val newY = y + yDir
        if (newX >= 0 && newX < seatMap[0].size && newY >= 0 && newY < seatMap.size) {
            val newSeat = seatMap[newY][newX]
            if (newSeat == 'L') {
                return 0
            } else if (newSeat == '#') {
                return 1
            }
        }
        return 0
    }

    fun occupiedSeatsAfterConvergenceWithVisibility(seats: List<String>): Int {
        var seatMap = seats.map { it.toCharArray() }
        while (true) {
            val nextSeats = mutableListOf<CharArray>()
            for (y in seats.indices) {
                nextSeats.add(seatMap[y].map { it }.toCharArray())
                for (x in seatMap[0].indices) {
                    if (nextSeats[y][x] == '.') {
                        continue
                    }
                    val adjacentSeats = numberOfVisibleOccupiedSeats(x, y, seatMap)
                    if (adjacentSeats >= 5) {
                        nextSeats[y][x] = 'L'
                    } else if (adjacentSeats == 0) {
                        nextSeats[y][x] = '#'
                    }

                }
            }
            val previousState = seatMap.joinToString { it.joinToString() }
            val newState = nextSeats.joinToString { it.joinToString() }
            if (newState.equals(previousState)) {
                return newState.count { it == '#' }
            }
            seatMap = nextSeats
        }
    }

    private fun numberOfVisibleOccupiedSeats(
        x: Int,
        y: Int,
        seatMap: List<CharArray>
    ): Int {
        var adjacentSeats = 0
        adjacentSeats += occupiedSeatInVisibleDirection(-1, 0, x, y, seatMap)
        adjacentSeats += occupiedSeatInVisibleDirection(1, 0, x, y, seatMap)
        adjacentSeats += occupiedSeatInVisibleDirection(0, -1, x, y, seatMap)
        adjacentSeats += occupiedSeatInVisibleDirection(0, 1, x, y, seatMap)
        adjacentSeats += occupiedSeatInVisibleDirection(-1, -1, x, y, seatMap)
        adjacentSeats += occupiedSeatInVisibleDirection(1, -1, x, y, seatMap)
        adjacentSeats += occupiedSeatInVisibleDirection(-1, 1, x, y, seatMap)
        adjacentSeats += occupiedSeatInVisibleDirection(1, 1, x, y, seatMap)
        return adjacentSeats
    }

    private fun occupiedSeatInVisibleDirection(xDir: Int, yDir: Int, x: Int, y: Int, seatMap: List<CharArray>): Int {
        var newX = x + xDir
        var newY = y + yDir
        while (newX >= 0 && newX < seatMap[0].size && newY >= 0 && newY < seatMap.size) {
            val newSeat = seatMap[newY][newX]
            if (newSeat == 'L') {
                return 0
            } else if (newSeat == '#') {
                return 1
            }
            newX += xDir
            newY += yDir
        }
        return 0
    }
}