class Day08 {
    fun programResult(instructions: List<String>): Pair<Boolean, Int> {
        val visited = mutableSetOf<Int>()
        var currentPosition = 0
        var accumulator = 0
        while (!visited.contains(currentPosition)) {
            visited.add(currentPosition)
            if (currentPosition == instructions.size) {
                return Pair(true, accumulator)
            }
            if (instructions[currentPosition].startsWith("acc")) {
                accumulator += instructions[currentPosition].substring(4).toInt()
                currentPosition++
            } else if (instructions[currentPosition].startsWith("jmp")) {
                currentPosition += instructions[currentPosition].substring(4).toInt()
            } else {
                currentPosition++
            }
        }
        return Pair(false, accumulator)
    }

    fun resultAfterFix(instructions: List<String>): Int {
        for ((i, instruction) in instructions.withIndex()) {
            val newInstructions = mutableListOf<String>()
            newInstructions.addAll(instructions)
            if (instruction.startsWith("acc")) {
                continue
            } else if (instruction.startsWith("jmp")) {
                newInstructions[i] = "nop" + instruction.substring(3)
            } else if (instruction.startsWith("nop")) {
                newInstructions[i] = "jmp" + instruction.substring(3)
            }

            val (programFinished, accumulatorValue) = programResult(newInstructions)
            if (programFinished) {
                return accumulatorValue
            }
        }
        return 0
    }
}