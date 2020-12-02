class Day01 {
    fun productOfTwoParts(numbers: List<Int>, sum: Int) : Int {
        val lookingFor = HashSet<Int>()
        for (number in numbers) {
            if (lookingFor.contains(number)) {
                return number * (sum - number)
            }
            lookingFor.add(sum - number)
        }
        return -1
    }

    fun productOfThreeParts(numbers: List<Int>, sum: Int) : Int {
        val lastIndex = numbers.size
        for ((index, number) in numbers.withIndex()) {
            val restOfNumbers = numbers.subList(index + 1, lastIndex)
            val productOfOtherTwoParts = productOfTwoParts(restOfNumbers, sum - number)
            if (productOfOtherTwoParts != -1) {
                return productOfOtherTwoParts * number
            }
        }
        return -1
    }
}