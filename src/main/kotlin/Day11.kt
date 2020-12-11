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
                    val adjacentSeats = numberOfAdjacentSeats(x, seatMap, y)
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

    private fun numberOfAdjacentSeats(
        x: Int,
        seatMap: List<CharArray>,
        y: Int
    ): Int {
        var adjacentSeats = 0
        if (x - 1 >= 0 && seatMap[y][x - 1] == '#') {
            adjacentSeats++
        }
        if (x + 1 < seatMap[0].size && seatMap[y][x + 1] == '#') {
            adjacentSeats++
        }
        if (y - 1 >= 0 && seatMap[y - 1][x] == '#') {
            adjacentSeats++
        }
        if (y + 1 < seatMap.size && seatMap[y + 1][x] == '#') {
            adjacentSeats++
        }
        if (x - 1 >= 0 && y - 1 >= 0 && seatMap[y - 1][x - 1] == '#') {
            adjacentSeats++
        }
        if (x + 1 < seatMap[0].size && y - 1 >= 0 && seatMap[y - 1][x + 1] == '#') {
            adjacentSeats++
        }
        if (x - 1 >= 0 && y + 1 < seatMap.size && seatMap[y + 1][x - 1] == '#') {
            adjacentSeats++
        }
        if (x + 1 < seatMap[0].size && y + 1 < seatMap.size && seatMap[y + 1][x + 1] == '#') {
            adjacentSeats++
        }
        return adjacentSeats
    }
}