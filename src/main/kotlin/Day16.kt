class Day16 {
    private val rulePattern = Regex("(.+): ([0-9]+)-([0-9]+) or ([0-9]+)-([0-9]+)")

    fun errorScanningRate(rules: List<String>, tickets: List<String>): Int {
        val parsedRules = parseRules(rules)
        return tickets.flatMap { it.split(",") }
            .map { it.toInt() }
            .filter { value -> !parsedRules.any { rule -> rule.matches(value) } }
            .sum()
    }

    private fun parseRules(rules: List<String>): List<Rule> {
        return rules.map { rulePattern.matchEntire(it) }
            .filter { it != null }
            .map { it!!.groupValues }
            .map { Rule(it[1], it[2].toInt(), it[3].toInt(), it[4].toInt(), it[5].toInt()) }
    }

    private data class Rule(val name: String, val min1: Int, val max1: Int, val min2: Int, val max2: Int) {
        fun matches(value: Int): Boolean {
            return (value in min1..max1) || (value in min2..max2)
        }
    }

    fun departureFields(rules: List<String>, tickets: List<String>, rawTicket: String): Long {
        val ticket = rawTicket.split(",").map { it.toLong() }
        var productOfDepartureFields = 1L
        val identifiedFields = identifiedFields(rules, tickets)
        for ((index, field) in identifiedFields.withIndex()) {
            if (field.startsWith("departure")) {
                productOfDepartureFields *= ticket[index]
            }
        }
        return productOfDepartureFields
    }

    fun identifiedFields(rules: List<String>, tickets: List<String>): List<String> {
        val parsedRules = parseRules(rules)
        val validTickets = tickets.map { it.split(",").map { it.toInt() } }
            .filter { isValid(it, parsedRules) }
        val matchingFields = mutableMapOf<Int, MutableSet<String>>()
        for (i in validTickets[0].indices) {
            matchingFields[i] = findAllMatchingRules(parsedRules, validTickets, i)
        }
        while (matchingFields.values.map { it.size }.any { it > 1 }) {
            val alreadyIdentifiedFields = matchingFields.values.filter { it.size == 1 }.map { it.first() }
            for (possibleFields in matchingFields.values.filter { it.size > 1 }) {
                possibleFields.removeAll(alreadyIdentifiedFields)
            }
        }
        return matchingFields.entries.sortedBy { it.key }.map { it.value.first() }
    }

    private fun findAllMatchingRules(
        parsedRules: List<Rule>,
        validTickets: List<List<Int>>,
        i: Int
    ): MutableSet<String> {
        return parsedRules.filter { rule -> !validTickets.map { it[i] }.any { !rule.matches(it) } }
            .map { it.name }
            .toMutableSet()
    }

    private fun isValid(ticket: List<Int>, parsedRules: List<Rule>): Boolean {
        return !ticket.any { value -> !parsedRules.any { rule -> rule.matches(value) } }
    }
}