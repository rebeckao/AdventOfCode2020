class Day15 {
    fun numberAfterTurns(startingNumbers: List<Int>, endTurn: Int): Int {
        var recentNumber = 0
        val recentlySpoken = mutableMapOf<Int, Int>()
        for (i in 0 until endTurn) {
            if (i < startingNumbers.size) {
                recentNumber = startingNumbers[i]
                recentlySpoken[recentNumber] = i
            } else if (recentlySpoken.containsKey(recentNumber)) {
                val lastSpoken = recentlySpoken[recentNumber]!!
                val newNumber = i - lastSpoken - 1
                recentlySpoken[recentNumber] = i - 1
                recentNumber = newNumber
            } else {
                recentlySpoken[recentNumber] = i - 1
                recentNumber = 0
            }
        }
        return recentNumber
    }
}