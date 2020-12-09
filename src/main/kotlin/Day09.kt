class Day09 {
    fun firstNumberNotMatching(numbers: List<Long>, preambleLength: Int): Long {
        val day01 = Day01()
        for (i in preambleLength until numbers.size) {
            val productOfParts = day01.productOfTwoParts(numbers.subList(i - preambleLength, i), numbers[i])
            if (productOfParts == -1L) {
                return numbers[i]
            }
        }
        return 0
    }
}