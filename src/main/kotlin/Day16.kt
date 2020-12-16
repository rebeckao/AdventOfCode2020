class Day16 {
    val rulePattern = Regex("(.+): ([0-9]+)-([0-9]+) or ([0-9]+)-([0-9]+)")

    fun errorScanningRate(rules: List<String>, tickets: List<String>): Int {
        val parsedRules = rules.map { rulePattern.matchEntire(it) }
            .filter { it != null }
            .map { it!!.groupValues }
            .map { Rule(it[2].toInt(), it[3].toInt(), it[4].toInt(), it[5].toInt()) }
        return tickets.flatMap { it.split(",") }
            .map { it.toInt() }
            .filter { value -> !parsedRules.any { rule -> rule.matches(value) } }
            .sum()
    }

    private data class Rule(val min1: Int, val max1: Int, val min2: Int, val max2: Int) {
        fun matches(value: Int): Boolean {
            return (value in min1..max1) || (value in min2..max2)
        }
    }
}