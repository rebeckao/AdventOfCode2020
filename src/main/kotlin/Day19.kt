class Day19 {
    private val leafRulePattern = Regex("\"(\\w)\"")

    fun rulesToRegex(rawRules: List<String>): String {
        val rules = parseRules(rawRules)
        return "^${toRegex(rules[0]!!, rules)}$"
    }

    private fun toRegex(currentRule: String, rules: Map<Int, String>): String {
        val matchResult = leafRulePattern.matchEntire(currentRule)
        if (matchResult != null) {
            return matchResult.groupValues[1]
        }

        if (currentRule.contains("|")) {
            val parts = currentRule.split("|")
            return "((${toRegex(parts[0], rules)})|(${toRegex(parts[1], rules)}))"
        }

        return currentRule.trim()
            .split(" ")
            .map { it.toInt() }
            .map { rules[it] }
            .joinToString("") { toRegex(it!!, rules) }
    }

    fun parseRules(rawRules: List<String>): Map<Int, String> {
        return rawRules
            .map { it.split(":") }
            .sortedBy { it[0] }
            .map { Pair(it[0].toInt(), it[1].trim()) }
            .toMap()
    }

    fun toModifiedRegex(currentRule: String, rules: Map<Int, String>, i: Int): String {
        if (i == 8) {
            return "(${toModifiedRegex(rules[42]!!, rules, 42)})+"
        }
        if (i == 11) {
            val rule42 = toModifiedRegex(rules[42]!!, rules, 42)
            val rule31 = toModifiedRegex(rules[31]!!, rules, 31)
            return "(" +
                    "($rule42$rule31)" +
                    "|($rule42$rule42$rule31$rule31)" +
                    "|($rule42$rule42$rule42$rule31$rule31$rule31)" +
                    "|($rule42$rule42$rule42$rule42$rule31$rule31$rule31$rule31)" +
                    "|($rule42$rule42$rule42$rule42$rule42$rule31$rule31$rule31$rule31$rule31)" +
                    ")"
        }

        val matchResult = leafRulePattern.matchEntire(currentRule)
        if (matchResult != null) {
            return matchResult.groupValues[1]
        }

        if (currentRule.contains("|")) {
            val parts = currentRule.split("|")
            val part1 = toModifiedRegex(parts[0], rules, -1)
            val part2 = toModifiedRegex(parts[1], rules, -1)
            return "(($part1)|($part2))"
        }

        return currentRule.trim()
            .split(" ")
            .map { it.toInt() }
            .joinToString("") { toModifiedRegex(rules[it]!!, rules, it) }
    }
}