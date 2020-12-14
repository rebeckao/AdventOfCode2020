class Day14 {
    private val maskPattern = Regex("mask = ([X10]+)")
    private val memPattern = Regex("mem\\[([0-9]+)] = ([0-9]+)")

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

    fun sumOfValuesAfterProgramV2(instructions: List<String>): Long {
        var mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
        val memory = mutableMapOf<String, Long>()
        var sum = 0L
        for (instruction in instructions) {
            val maskMatch = maskPattern.matchEntire(instruction)
            if (maskMatch != null) {
                mask = maskMatch.groupValues[1]
            } else if (memPattern.matches(instruction)) {
                val memMatch = memPattern.matchEntire(instruction)!!.groupValues
                val target = memMatch[1]
                    .toInt()
                    .toString(2)
                    .padStart(36, '0')
                val value = memMatch[2].toLong()
                val maskedTargets = applyMaskV2(mask, target)
                for (maskedTarget in maskedTargets) {
                    if (memory.containsKey(maskedTarget)) {
                        sum -= memory[maskedTarget]!!
                    }
                    memory[maskedTarget] = value
                }
                sum += maskedTargets.size * value
            } else {
                throw IllegalStateException("Not recognized: $instruction")
            }
        }
        return sum
    }

    fun applyMaskV2(mask: String, value: String): Set<String> {
        val mutableValue = value.toCharArray()
        for ((index, bit) in mask.withIndex()) {
            if (bit == '0') {
                continue
            } else if (bit == '1') {
                mutableValue[index] = '1'
            } else {
                val valueSoFar = mutableValue.slice(0 until index).joinToString("")
                val restOfMask = mask.substring(index + 1)
                val restOfValue = value.substring(index + 1)
                val possibilities = applyMaskV2(restOfMask, restOfValue)
                return possibilities.flatMap { setOf(valueSoFar + "1" + it, valueSoFar + "0" + it) }.toSet()
            }
        }
        return setOf(mutableValue.joinToString(""))
    }
}