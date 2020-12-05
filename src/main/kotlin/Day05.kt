class Day05 {
    fun seatNumber(seat: String): Int {
        val binary = seat.replace('B', '1')
            .replace('F', '0')
            .replace('R', '1')
            .replace('L', '0')
        return binaryToDecimal(binary)
    }

    fun binaryToDecimal(binary: String): Int {
        val numberOfDigits = binary.length
        var decimal = 0
        for ((index, digit) in binary.withIndex()) {
            val position = numberOfDigits - index - 1
            val value: Int = Math.pow(2.0, position * 1.0).toInt()
            val digitValue = Character.getNumericValue(digit)
            decimal += value * digitValue
        }
        return decimal
    }

    fun missingNumber(sorted: IntArray): Int {
        val lowestNumber = sorted[0]
        for ((index, number) in sorted.withIndex()) {
            if (number != index + lowestNumber) {
                return index + lowestNumber
            }
        }
        return -1
    }
}