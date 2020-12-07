import java.util.*

class Day07 {
    private val rulePattern = Regex("(.+) bags contain (\\d+) (\\w+ \\w+) bags?(.*)\\.")
    private val additionalBagChildPattern = Regex(", (\\d+) (\\w+ \\w+) bags?")

    fun numberOfColorsThatCanContain(rules: List<String>, targetColor: String): Int {
        val bagParentsByColor = parseToBagParents(rules)
        val colorsToInvestigate = Stack<String>()
        colorsToInvestigate.addAll(bagParentsByColor.getOrDefault(targetColor, emptySet<String>()))
        val foundColors = mutableSetOf<String>()
        while (colorsToInvestigate.isNotEmpty()) {
            val currentColor = colorsToInvestigate.pop()
            if (foundColors.contains(currentColor)) {
                continue
            } else {
                foundColors.add(currentColor)
                colorsToInvestigate.addAll(bagParentsByColor.getOrDefault(currentColor, emptySet<String>()))
            }
        }
        return foundColors.size
    }

    private fun parseToBagParents(rules: List<String>): MutableMap<String, MutableSet<String>> {
        val bagParentsByColor = mutableMapOf<String, MutableSet<String>>()
        rules.filter { it.matches(rulePattern) }
            .map { rulePattern.matchEntire(it)!!.groupValues }
            .forEach {
                val outerBagColor = it[1]
                val firstInnerBagColor = it[3]
                val restOfRule = it[4]
                for (containedColor in allBagsInside(firstInnerBagColor, restOfRule)) {
                    bagParentsByColor.getOrPut(containedColor, { mutableSetOf() }).add(outerBagColor)
                }
            }
        return bagParentsByColor
    }

    private fun allBagsInside(
        colorContained: String,
        restOfRule: String
    ): Set<String> {
        val bagsInside = mutableSetOf(colorContained)
        val additionalColors = additionalBagChildPattern.findAll(restOfRule)
        for (match in additionalColors) {
            bagsInside.add(match.groupValues[2])
        }
        return bagsInside
    }

    fun numberOfBagsInside(rules: List<String>, targetColor: String): Int {
        return numberOfBagsInsideRecursive(targetColor, parseToBagChildren(rules))
    }

    private fun parseToBagChildren(rules: List<String>): Map<String, Set<Pair<Int, String>>> {
        val bagChildrenByColor = rules.filter { it.matches(rulePattern) }
            .map { rulePattern.matchEntire(it)!!.groupValues }
            .map { Pair(it[1], allQuantitiesAndBagsInside(it[2].toInt(), it[3], it[4])) }
            .toMap()
        return bagChildrenByColor
    }

    private fun allQuantitiesAndBagsInside(
        quantityContained: Int,
        colorContained: String,
        restOfRule: String
    ): Set<Pair<Int, String>> {
        val bagsInside = mutableSetOf(Pair(quantityContained, colorContained))
        val additionalColors = additionalBagChildPattern.findAll(restOfRule)
        for (match in additionalColors) {
            bagsInside.add(Pair(match.groupValues[1].toInt(), match.groupValues[2]))
        }
        return bagsInside
    }

    private fun numberOfBagsInsideRecursive(color: String, bagChildrenByColor: Map<String, Set<Pair<Int, String>>>) : Int {
        var quantity = 0
        for (bags in bagChildrenByColor.getOrDefault(color, emptySet())) {
            quantity += bags.first * (1 + numberOfBagsInsideRecursive(bags.second, bagChildrenByColor))
        }
        return quantity
    }
}