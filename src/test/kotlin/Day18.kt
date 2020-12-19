class Day18 {
    fun evaluateExpressionLeftPrecedence(rawExpression: String): Long {
        val expression = rawExpression.replace(" ", "")
        return evaluateInnerExpressionLeftPrecedence(0, expression).second
    }

    private fun evaluateInnerExpressionLeftPrecedence(initialIndex: Int, expression: String): Pair<Int, Long> {
        var resultSoFar = 0L
        var isPlus = true
        var index = initialIndex
        while (index < expression.length) {
            val currentElement = expression[index]
            if (currentElement == ')') {
                return Pair(index, resultSoFar)
            }
            if (currentElement == '+') {
                isPlus = true
                index++
                continue
            }
            if (currentElement == '*') {
                isPlus = false
                index++
                continue
            }

            var value: Long
            if (currentElement == '(') {
                index++
                val result = evaluateInnerExpressionLeftPrecedence(index, expression)
                index = result.first
                value = result.second
            } else {
                value = Character.getNumericValue(currentElement).toLong()
            }

            if (isPlus) {
                resultSoFar += value
            } else {
                resultSoFar *= value
            }

            index++
        }
        return Pair(index, resultSoFar)
    }
}