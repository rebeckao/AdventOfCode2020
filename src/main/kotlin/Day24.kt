class Day24 {
    fun blackTiles(tileReferences: List<String>): Int {
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
        return flippedTiles.values.filter { it }.count()
    }
}