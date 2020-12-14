class Day14 {
    private val maskPattern = Regex("mask = ([X10]+)")
    private val memPattern = Regex("mem\\[([0-9]+)\\] = ([0-9]+)")

    fun sumOfValuesAfterProgram(instructions: List<String>): Long {
        var mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
        val memory = mutableMapOf<Int, String>()
        for (instruction in instructions) {
            val maskMatch = maskPattern.matchEntire(instruction)
            if (maskMatch != null) {
                mask = maskMatch.groupValues[1]
            } else if (memPattern.matches(instruction)) {
                val memMatch = memPattern.matchEntire(instruction)!!.groupValues
                val target = memMatch[1].toInt()
                val value = memMatch[2].toInt().toString(2)
                val maskedValue = applyMask(mask, value)
                memory[target] = maskedValue
            } else {
                throw IllegalStateException("Not recognized: $instruction")
            }
        }
        return memory.values
            .map { it.toLong(2) }
            .sum()
    }

    fun applyMask(mask: String, value: String): String {
        val mutableValue = value
            .padStart(36, '0')
            .toCharArray()
        for ((index, bit) in mask.withIndex()) {
            if (bit == 'X') {
                continue
            }
            mutableValue[index] = bit
        }
        return mutableValue.joinToString("")
    }
}