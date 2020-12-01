class Day01 {
    fun productOfTwo2020Parts(numbers: List<Int>) : Int {
        val lookingFor = HashSet<Int>()
        for (number in numbers) {
            if (lookingFor.contains(number)) {
                return number * (2020 - number)
            }
            lookingFor.add(2020 - number)
        }
        return -1
    }

    fun productOfThree2020Parts(numbers: List<Int>) : Int {
        val lastIndex = numbers.size
        for ((index, number) in numbers.withIndex()) {
            for ((otherIndex, otherNumber) in numbers.subList(index + 1, lastIndex).withIndex()) {
                if (number + otherNumber <= 2020) {
                    for (thirdNumber in numbers.subList(otherIndex + 1, lastIndex)) {
                        if (number + otherNumber + thirdNumber == 2020) {
                            return number * otherNumber * thirdNumber
                        }
                    }
                }
            }
        }

        return -1
    }
}