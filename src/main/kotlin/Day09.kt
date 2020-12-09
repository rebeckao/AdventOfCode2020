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

    fun contiguousSetSummingTo(target: Long, numbers: List<Long>): Long {
        for ((i, number) in numbers.withIndex()) {
            var sum = number
            var min = number
            var max = number
            var index = i
            while (sum < target) {
                index++
                val newNumber = numbers[index]
                sum += newNumber
                if (newNumber < min) {
                    min = newNumber
                } else if (newNumber > max) {
                    max = newNumber
                }
                if (sum == target) {
                    return min + max
                }
            }
        }
        return 0
    }
}