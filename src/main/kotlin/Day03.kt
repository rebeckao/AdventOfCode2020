class Day03 {
    fun numberOfTreesInPath(map : List<String>, slopeRight: Int, slopeDown: Int) : Long {
        var x = 0
        var y = 0
        var trees : Long = 0
        val mapWidth = map[0].length
        while (y < map.size) {
            if (map[y][x] == '#') {
                trees++
            }
            y += slopeDown
            x += slopeRight
            x %= mapWidth
        }
        return trees
    }
}