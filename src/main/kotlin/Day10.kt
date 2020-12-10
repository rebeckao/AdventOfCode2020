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

    fun numberOfArrangements(adapters: List<Int>): Long {
        var prevAdapter = 0
        var currentSegmentLength = 1
        val segmentLengths = mutableListOf<Int>()
        for (adapter in adapters.sorted()) {
            val difference = adapter - prevAdapter
            if (difference == 1) {
                currentSegmentLength++
            } else if (currentSegmentLength != 1) {
                segmentLengths.add(currentSegmentLength)
                currentSegmentLength = 1
            }
            prevAdapter = adapter
        }
        if (currentSegmentLength != 1) {
            segmentLengths.add(currentSegmentLength)
        }
        return segmentLengths.map { numberOfArrangementsForLength(it) }.reduce { a, b -> a * b }
    }

    private fun numberOfArrangementsForLength(length: Int): Long {
        return when (length) {
            2 -> 1
            3 -> 2
            4 -> 4
            5 -> 7
            else -> {
                throw IllegalStateException("No hard coded number of arrangement for $length adapters with consecutive joltages")
            }
        }
    }
}