import kotlin.streams.toList

class Day20 {
    private val tileIdPattern = Regex("^Tile (\\d+):")

    fun productOfCornerTileIds(rawTiles: List<List<String>>): Long {
        val sidesAndIds = mutableMapOf<String, MutableSet<Long>>()
        val tiles = rawTiles.map { toTile(it) }
        for (tile in tiles) {
            for (side in tile.sides) {
                sidesAndIds.putIfAbsent(side, mutableSetOf())
                sidesAndIds[side]!!.add(tile.tileId)
            }
        }
        val cornerTiles = tiles.stream()
            .filter { numberOfSidesNotMatchingOtherTiles(it, sidesAndIds) == 2 }
            .mapToLong { it.tileId }
            .toList()
        return cornerTiles.reduce { a, b -> a * b }
    }

    private fun numberOfSidesNotMatchingOtherTiles(
        it: Tile,
        sidesAndIds: Map<String, Set<Long>>
    ): Int {
        return it.sides.filter { side ->
            !otherTileMatchesSide(sidesAndIds, side, it)
                    && !otherTileMatchesSide(sidesAndIds, side.reversed(), it)
        }.count()
    }

    private fun otherTileMatchesSide(
        sidesAndIds: Map<String, Set<Long>>,
        side: String,
        thisTile: Tile
    ): Boolean {
        val tiles = sidesAndIds[side] ?: return false
        return tiles.any { id -> id != thisTile.tileId }
    }

    private fun toTile(rawTile: List<String>): Tile {
        val tileId = tileIdPattern.matchEntire(rawTile[0])!!.groupValues[1].toLong()
        val lastRow = rawTile.size - 1
        val lastCol = rawTile[1].length - 1

        val top = rawTile[1]
        val right = (1..lastRow).map { rawTile[it] }.map { it[lastCol] }.joinToString("")
        val bottom = rawTile[lastRow].reversed()
        val left = (lastRow downTo 1).map { rawTile[it] }.map { it[0] }.joinToString("")
        val sides = listOf(
            top,
            right,
            bottom,
            left
        )

        return Tile(tileId, sides)
    }

    private data class Tile(val tileId: Long, val sides: List<String>)
}