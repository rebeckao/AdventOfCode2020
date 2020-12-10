class Day10 {
    fun joltageDistributions(adapters: List<Int>): Pair<Int, Int> {
        var prevAdapter = 0
        val differenceDistributions = mutableMapOf<Int, Int>()
        for (adapter in adapters.sorted()) {
            val difference = adapter - prevAdapter
            differenceDistributions[difference] = differenceDistributions.getOrDefault(difference, 0) + 1
            prevAdapter = adapter
        }
        val oneJoltDifferences = differenceDistributions.getOrDefault(1, 0)
        val threeJoltDifferences = differenceDistributions.getOrDefault(3, 0) + 1
        return Pair(oneJoltDifferences, threeJoltDifferences)
    }
}