class Day24 {
    fun blackTiles(tileReferences: List<String>): Int {
        return initialTiles(tileReferences).values.filter { it }.count()
    }

    fun blackTilesAfterDays(tileReferences: List<String>, days: Int): Int {
        var tiles = initialTiles(tileReferences)
        for (day in 0 until days) {
            val newTiles = mutableMapOf<Pair<Int, Int>, Boolean>()

            val (xMin, xMax) = xLimits(tiles)
            val (yMin, yMax) = yLimits(tiles)

            for (x in xMin..xMax) {
                for (y in yMin..yMax) {
                    val coordinates = Pair(x, y)
                    val numberOfBlackNeighbours: Int = numberOfBlackNeighbours(tiles, coordinates)
                    if (tiles.getOrDefault(coordinates, false)) {
                        newTiles[coordinates] = !(numberOfBlackNeighbours == 0 || numberOfBlackNeighbours > 2)
                    } else {
                        if (numberOfBlackNeighbours == 2) {
                            newTiles[coordinates] = true
                        }
                    }
                }
            }
            tiles = newTiles
        }
        return tiles.values.filter { it }.count()
    }

    private fun xLimits(tiles: Map<Pair<Int, Int>, Boolean>): Pair<Int, Int> {
        val xValues = tiles.keys.map { it.first }
        val xMin = (xValues.minOrNull() ?: 0) - 2
        val xMax = (xValues.maxOrNull() ?: 0) + 2
        return Pair(xMin, xMax)
    }

    private fun yLimits(tiles: Map<Pair<Int, Int>, Boolean>): Pair<Int, Int> {
        val yValues = tiles.keys.map { it.second }
        val yMin = (yValues.minOrNull() ?: 0) - 2
        val yMax = (yValues.maxOrNull() ?: 0) + 2
        return Pair(yMin, yMax)
    }

    private fun numberOfBlackNeighbours(tiles: Map<Pair<Int, Int>, Boolean>, coordinates: Pair<Int, Int>): Int {
        val (x, y) = coordinates
        return listOf(
            Pair(x + 2, y),
            Pair(x + 1, y - 2),
            Pair(x - 1, y - 2),
            Pair(x - 2, y),
            Pair(x - 1, y + 2),
            Pair(x + 1, y + 2)
        )
            .filter { tiles.getOrDefault(it, false) }
            .count()

    }

    private fun initialTiles(tileReferences: List<String>): Map<Pair<Int, Int>, Boolean> {
        val flippedTiles = mutableMapOf<Pair<Int, Int>, Boolean>()
        for (reference in tileReferences) {
            var i = 0
            var x = 0
            var y = 0
            while (i < reference.length) {
                when (reference[i]) {
                    'e' -> {
                        x += 2
                        i++
                    }
                    's' -> {
                        y -= 2
                        if (reference[i + 1] == 'e') {
                            x++
                        } else {
                            x--
                        }
                        i += 2
                    }
                    'w' -> {
                        x -= 2
                        i++
                    }
                    'n' -> {
                        y += 2
                        if (reference[i + 1] == 'w') {
                            x--
                        } else {
                            x++
                        }
                        i += 2
                    }
                }
            }
            val coordinates = Pair(x, y)
            flippedTiles[coordinates] = !flippedTiles.getOrDefault(coordinates, false)
        }
        return flippedTiles
    }
}