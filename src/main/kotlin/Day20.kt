import kotlin.math.sqrt
import kotlin.streams.toList

class Day20 {
    private val tileIdPattern = Regex("^Tile (\\d+):")

    fun productOfCornerTileIds(rawTiles: List<List<String>>): Long {
        val tiles = parseTiles(rawTiles)
        val sidesAndIds = sidesAndIds(tiles)
        val cornerTiles = cornerTiles(tiles, sidesAndIds)
            .stream()
            .mapToLong { it.tileId }
        return cornerTiles.reduce { a, b -> a * b }.orElse(0)
    }

    /**
     * Looking for:
     * "                  # "
     * "#    ##    ##    ###"
     * " #  #  #  #  #  #   "
     **/
    fun markSeaMonsters(map: List<String>): List<String> {
        val monsterPatternTop = Regex("(..................)[#O](.)")
        val monsterPatternMiddle = Regex("[#O](....)[#O]{2}(....)[#O]{2}(....)[#O]{3}")
        val monsterPatternBottom = Regex("(.)[#O](..)[#O](..)[#O](..)[#O](..)[#O](..)[#O](...)")
        val monsterLength = 20
        val newMap = map.toMutableList()
        for (rowId in 0 until map.size - 2) {
            for (col in 0..map[0].length - monsterLength) {
                val topRow = newMap[rowId]
                val middleRow = newMap[rowId + 1]
                val bottomRow = newMap[rowId + 2]
                val topString = topRow.substring(col, col + monsterLength)
                val middleString = middleRow.substring(col, col + monsterLength)
                val bottomString = bottomRow.substring(col, col + monsterLength)
                if (monsterPatternTop.matches(topString) && monsterPatternMiddle.matches(middleString) && monsterPatternBottom.matches(
                        bottomString
                    )
                ) {
                    val newTopPart = topString.replace(monsterPatternTop, "$1O$2")
                    val newMiddlePart = middleString.replace(monsterPatternMiddle, "O$1OO$2OO$3OOO")
                    val newBottomPart = bottomString.replace(monsterPatternBottom, "$1O$2O$3O$4O$5O$6O$7")
                    newMap[rowId] =
                        topRow.substring(0, col) + newTopPart + topRow.substring(col + monsterLength)
                    newMap[rowId + 1] =
                        middleRow.substring(0, col) + newMiddlePart + middleRow.substring(col + monsterLength)
                    newMap[rowId + 2] =
                        bottomRow.substring(0, col) + newBottomPart + bottomRow.substring(col + monsterLength)
                }
            }
        }
        return newMap
    }

    fun markSeaMonstersInAllDirections(map: List<String>): List<String> {
        var newMap = map

        newMap = markSeaMonstersGoingLeftAndRight(newMap)
        newMap = rotateClockwise(newMap, 1)
        newMap = markSeaMonstersGoingLeftAndRight(newMap)
        newMap = rotateClockwise(newMap, 1)
        newMap = markSeaMonstersGoingLeftAndRight(newMap)
        newMap = rotateClockwise(newMap, 1)
        newMap = markSeaMonstersGoingLeftAndRight(newMap)

        newMap = rotateClockwise(newMap, 1)
        return newMap
    }

    fun waterRoughness(rawTiles: List<List<String>>): Int {
        val completeMap = toCompleteMap(rawTiles)
        val marked = markSeaMonstersInAllDirections(completeMap)
        return marked.map { it.filter { c -> c == '#' }.count() }.sum()
    }

    private fun markSeaMonstersGoingLeftAndRight(map: List<String>): List<String> {
        var newMap = map
        newMap = markSeaMonsters(newMap)
        newMap = flipLeftToRight(newMap)
        newMap = markSeaMonsters(newMap)
        newMap = flipLeftToRight(newMap)
        return newMap
    }

    private fun cornerTiles(
        tiles: List<Tile>,
        sidesAndIds: MutableMap<String, MutableSet<Long>>
    ): List<Tile> {
        return tiles.stream()
            .filter { numberOfSidesNotMatchingOtherTiles(it, sidesAndIds) == 2 }
            .toList()
    }

    private fun numberOfSidesNotMatchingOtherTiles(
        it: Tile,
        sidesAndIds: Map<String, Set<Long>>
    ): Int {
        return it.sides.filter { side ->
            !otherTileMatchesSide(sidesAndIds, side, it.tileId)
                    && !otherTileMatchesSide(sidesAndIds, side.reversed(), it.tileId)
        }.count()
    }

    private fun otherTileMatchesSide(
        sidesAndIds: Map<String, Set<Long>>,
        side: String,
        thisTileId: Long
    ): Boolean {
        val tiles = sidesAndIds[side] ?: return false
        return tiles.any { id -> id != thisTileId }
    }

    fun toCompleteMap(rawTiles: List<List<String>>): List<String> {
        val tiles = parseTiles(rawTiles)
        val sidesAndIds = sidesAndIds(tiles)
        val tilesToASide = sqrt(tiles.size.toDouble()).toInt()
        val cornerTiles = cornerTiles(tiles, sidesAndIds)
        val startingTile = cornerTiles[0]

        val rootTile = toStartingConnectedTile(startingTile, sidesAndIds)
        val topRowOfTiles = mutableListOf(rootTile)
        for (col in 1 until tilesToASide) {
            val previousTile = topRowOfTiles[col - 1]
            topRowOfTiles.add(connectedTileMatchingRightSideOfPrevious(previousTile, sidesAndIds, tiles))
        }
        val allRowsOfTiles = mutableListOf(topRowOfTiles)
        for (nextRow in 1 until tilesToASide) {
            val previousRow = allRowsOfTiles[nextRow - 1]
            val newRow = mutableListOf<ConnectedTile>()
            for (col in 0 until tilesToASide) {
                val previousTile = previousRow[col]
                newRow.add(connectedTileMatchingBottomSideOfPrevious(previousTile, sidesAndIds, tiles))
            }
            allRowsOfTiles.add(newRow)
        }

        val tileSideLength = allRowsOfTiles[0][0].innerMap[0].length
        return allRowsOfTiles.flatMap { tileRow ->
            (0 until tileSideLength).map { row ->
                tileRow.joinToString("") { it.innerMap[row] }
            }
        }
    }

    private fun connectedTileMatchingRightSideOfPrevious(
        previousTile: ConnectedTile,
        sidesAndIds: MutableMap<String, MutableSet<Long>>,
        tiles: List<Tile>
    ): ConnectedTile {
        val sideToMatch = previousTile.right
        val nextTileId: Long = nextTileId(sidesAndIds, sideToMatch, previousTile)
        val nextTile = tiles.first { it.tileId == nextTileId }
        val top = nextTile.sides[0]
        val right = nextTile.sides[1]
        val bottom = nextTile.sides[2]
        val left = nextTile.sides[3]
        return when (sideToMatch) {
            top -> {
                ConnectedTile(
                    flipLeftToRight(rotateClockwise(nextTile.innerMap, 1)),
                    nextTileId,
                    left.reversed(),
                    bottom.reversed(),
                    right.reversed(),
                    top.reversed()
                )
            }
            top.reversed() -> {
                ConnectedTile(rotateClockwise(nextTile.innerMap, 3), nextTileId, right, bottom, left, top)
            }
            right -> {
                ConnectedTile(
                    flipLeftToRight(nextTile.innerMap),
                    nextTileId,
                    top.reversed(),
                    left.reversed(),
                    bottom.reversed(),
                    right.reversed()
                )
            }
            right.reversed() -> {
                ConnectedTile(rotateClockwise(nextTile.innerMap, 2), nextTileId, bottom, left, top, right)
            }
            bottom -> {
                ConnectedTile(
                    flipTopToBottom(rotateClockwise(nextTile.innerMap, 1)),
                    nextTileId,
                    right.reversed(),
                    top.reversed(),
                    left.reversed(),
                    bottom.reversed()
                )
            }
            bottom.reversed() -> {
                ConnectedTile(rotateClockwise(nextTile.innerMap, 1), nextTileId, left, top, right, bottom)
            }
            left -> {
                ConnectedTile(
                    flipTopToBottom(nextTile.innerMap),
                    nextTileId,
                    bottom.reversed(),
                    right.reversed(),
                    top.reversed(),
                    left.reversed()
                )
            }
            else -> {// sideToMatch == left.reversed()
                ConnectedTile(nextTile.innerMap, nextTileId, top, right, bottom, left)
            }
        }
    }

    private fun nextTileId(
        sidesAndIds: Map<String, MutableSet<Long>>,
        sideToMatch: String,
        previousTile: ConnectedTile
    ): Long {
        if (sidesAndIds[sideToMatch] != null) {
            val matchingTiles = sidesAndIds[sideToMatch]!!.filter { it != previousTile.tileId }
            if (matchingTiles.size == 1) {
                return matchingTiles.first()
            }
        }

        if (sidesAndIds[sideToMatch.reversed()] != null) {
            val matchingTiles = sidesAndIds[sideToMatch.reversed()]!!.filter { it != previousTile.tileId }
            if (matchingTiles.size == 1) {
                return matchingTiles.first()
            }
        }
        throw IllegalStateException("Mo match found for $sideToMatch")
    }

    private fun connectedTileMatchingBottomSideOfPrevious(
        previousTile: ConnectedTile,
        sidesAndIds: MutableMap<String, MutableSet<Long>>,
        tiles: List<Tile>
    ): ConnectedTile {
        val sideToMatch = previousTile.bottom
        val nextTileId: Long = nextTileId(sidesAndIds, sideToMatch, previousTile)
        val nextTile = tiles.first { it.tileId == nextTileId }
        val top = nextTile.sides[0]
        val right = nextTile.sides[1]
        val bottom = nextTile.sides[2]
        val left = nextTile.sides[3]
        return when (sideToMatch) {
            right -> {
                ConnectedTile(
                    flipTopToBottom(rotateClockwise(nextTile.innerMap, 1)),
                    nextTileId,
                    right.reversed(),
                    top.reversed(),
                    left.reversed(),
                    bottom.reversed()
                )
            }
            right.reversed() -> {
                ConnectedTile(rotateClockwise(nextTile.innerMap, 3), nextTileId, right, bottom, left, top)
            }
            bottom -> {
                ConnectedTile(
                    flipTopToBottom(nextTile.innerMap),
                    nextTileId,
                    bottom.reversed(),
                    right.reversed(),
                    top.reversed(),
                    left.reversed()
                )
            }
            bottom.reversed() -> {
                ConnectedTile(rotateClockwise(nextTile.innerMap, 2), nextTileId, bottom, left, top, right)
            }
            left -> {
                ConnectedTile(
                    flipLeftToRight(rotateClockwise(nextTile.innerMap, 1)),
                    nextTileId,
                    left.reversed(),
                    bottom.reversed(),
                    right.reversed(),
                    top.reversed()
                )
            }
            left.reversed() -> {
                ConnectedTile(rotateClockwise(nextTile.innerMap, 1), nextTileId, left, top, right, bottom)
            }
            top -> {
                ConnectedTile(
                    flipLeftToRight(nextTile.innerMap),
                    nextTileId,
                    top.reversed(),
                    left.reversed(),
                    bottom.reversed(),
                    right.reversed()
                )
            }
            else -> {// sideToMatch == top.reversed()
                ConnectedTile(nextTile.innerMap, nextTileId, top, right, bottom, left)
            }
        }
    }

    fun flipLeftToRight(innerMap: List<String>): List<String> {
        return innerMap.map { it.reversed() }
    }

    private fun flipTopToBottom(innerMap: List<String>): List<String> {
        return innerMap.reversed()
    }

    private fun toStartingConnectedTile(
        startingTile: Tile,
        sidesAndIds: MutableMap<String, MutableSet<Long>>
    ): ConnectedTile {
        val top = startingTile.sides[0]
        val right = startingTile.sides[1]
        val bottom = startingTile.sides[2]
        val left = startingTile.sides[3]
        val rootTileId = startingTile.tileId
        val topMatches = otherTileMatchesSide(sidesAndIds, top, rootTileId)
                || otherTileMatchesSide(sidesAndIds, top.reversed(), rootTileId)
        val rightMatches = otherTileMatchesSide(sidesAndIds, right, rootTileId)
                || otherTileMatchesSide(sidesAndIds, right.reversed(), rootTileId)
        return if (topMatches) {
            if (rightMatches) {
                ConnectedTile(
                    flipTopToBottom(startingTile.innerMap),
                    rootTileId,
                    bottom.reversed(),
                    right.reversed(),
                    top.reversed(),
                    left.reversed()
                )
            } else {
                ConnectedTile(rotateClockwise(startingTile.innerMap, 2), rootTileId, bottom, left, top, right)
            }
        } else {
            if (rightMatches) {
                ConnectedTile(startingTile.innerMap, rootTileId, top, right, bottom, left)
            } else {
                ConnectedTile(rotateClockwise(startingTile.innerMap, 3), rootTileId, right, bottom, left, top)
            }
        }
    }

    fun rotateClockwise(map: List<String>, times: Int): List<String> {
        val height = map.size
        val width = map[0].length
        var oldMap = map
        var newMap = map
        for (time in 0 until times) {
            newMap = (0 until width).map { row ->
                (0 until height).map { col ->
                    oldMap[height - col - 1][row]
                }.joinToString("")
            }.toList()
            oldMap = newMap
        }
        return newMap
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

        return Tile(tileId, sides, (2 until lastRow).map { rawTile[it] }.map { it.substring(1, lastCol) })
    }

    private fun sidesAndIds(tiles: List<Tile>): MutableMap<String, MutableSet<Long>> {
        val sidesAndIds = mutableMapOf<String, MutableSet<Long>>()
        for (tile in tiles) {
            for (side in tile.sides) {
                sidesAndIds.putIfAbsent(side, mutableSetOf())
                sidesAndIds[side]!!.add(tile.tileId)
            }
        }
        return sidesAndIds
    }

    private fun parseTiles(rawTiles: List<List<String>>): List<Tile> {
        return rawTiles.map { toTile(it) }
    }

    private data class Tile(val tileId: Long, val sides: List<String>, val innerMap: List<String>)

    private data class ConnectedTile(
        val innerMap: List<String>,
        val tileId: Long,
        val top: String,
        val right: String,
        val bottom: String,
        val left: String
    )
}