class Day17in4D {
    fun cubesAfterCycles(cycles: Int, initialState: List<String>): Int {
        var cubeSpace = initialize(initialState)
        val minX = -cycles - 1
        val maxX = initialState[0].length + cycles + 1
        val minY = -cycles - 1
        val maxY = initialState.size + cycles + 1
        val minZ = -cycles - 1
        val maxZ = cycles + 1
        val minW = -cycles - 1
        val maxW = cycles + 1
        for (cycle in 0 until cycles) {
            val newCubeSpace = mutableMapOf<Coordinate, Boolean>()
            for (x in minX until maxX) {
                for (y in minY until maxY) {
                    for (z in minZ until maxZ) {
                        for (w in minW until maxW) {
                            val currentlyActive = isActive(cubeSpace, x, y, z, w)
                            val activeNeighbors = activeNeighbors(x, y, z, w, cubeSpace)
                            val shouldBeActive = shouldBeActive(currentlyActive, activeNeighbors)
                            newCubeSpace[Coordinate(x, y, z, w)] = shouldBeActive
                        }
                    }
                }
            }
            cubeSpace = newCubeSpace
        }
        return cubeSpace.values.filter { it }.count()
    }

    private fun initialize(initialState: List<String>): MutableMap<Coordinate, Boolean> {
        val space = mutableMapOf<Coordinate, Boolean>()
        for ((y, row) in initialState.withIndex()) {
            for ((x, value) in row.withIndex()) {
                space.put(Coordinate(x, y, 0, 0), value == '#')
            }
        }
        return space
    }

    private fun shouldBeActive(currentlyActive: Boolean, activeNeighbors: Int): Boolean {
        if (currentlyActive) {
            return activeNeighbors == 2 || activeNeighbors == 3
        } else {
            return activeNeighbors == 3
        }
    }

    private fun isActive(
        cubeSpace: Map<Coordinate, Boolean>,
        x: Int,
        y: Int,
        z: Int,
        w: Int
    ) = cubeSpace.getOrDefault(Coordinate(x, y, z, w), false)

    private fun activeNeighbors(x: Int, y: Int, z: Int, w: Int, cubeSpace: Map<Coordinate, Boolean>): Int {
        return (x - 1..x + 1).flatMap { nX ->
            (y - 1..y + 1).flatMap { nY ->
                (z - 1..z + 1).flatMap { nZ ->
                    (w - 1..w + 1).filter { nW ->
                        if (!(nX == x && nY == y && nZ == z && nW == w)) {
                            isActive(cubeSpace, nX, nY, nZ, nW)
                        } else {
                            false
                        }
                    }
                }
            }
        }.size
    }

    private data class Coordinate(val x: Int, val y: Int, val z: Int, val w: Int)
}