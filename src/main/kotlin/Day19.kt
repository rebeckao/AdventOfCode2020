class Day19 {
    private val leafRulePattern = Regex("\"(\\w)\"")

    fun rulesToRegex(rawRules: List<String>): String {
        val rules = rawRules
            .map { it.split(":") }
            .sortedBy { it[0] }
            .map { Pair(it[0].toInt(), it[1].trim()) }
            .toMap()
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
}