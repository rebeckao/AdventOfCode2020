import java.util.*

class Day23 {
    fun crabCups(initialCups: String, moves: Int): String {
        val cups = LinkedList(initialCups.map { Character.getNumericValue(it) })
        for (move in 0 until moves) {
            val currentCup = cups.pop()
            val floatingCups = listOf(cups.pop(), cups.pop(), cups.pop())
            val destinationCupLabel =
                cups.minByOrNull { Math.floorMod(currentCup - it, initialCups.length) } ?: throw IllegalStateException()
            val destination = cups.indexOf(destinationCupLabel) + 1
            cups.addAll(destination, floatingCups)
            cups.addLast(currentCup)
        }
        val indexOf1 = cups.indexOf(1)
        val firstPart = cups.subList((indexOf1 + 1) % cups.size, cups.size).joinToString("")
        val secondPart = cups.subList(0, indexOf1).joinToString("")
        return firstPart + secondPart
    }

    fun manyCrabCups(initialCups: String, numberOfCups: Int, moves: Int): Long {
        val initialLinkedCups = toInitialLinkedCups(initialCups)
        var previousCupByLabel = initialLinkedCups.first { it.label == initialCups.length }
        var previousCupInList = initialLinkedCups[initialLinkedCups.lastIndex]
        for (label in 10..numberOfCups) {
            val newCup = LinkedCup(label, previousCupByLabel, null)
            previousCupInList.nextCup = newCup
            previousCupByLabel = newCup
            previousCupInList = newCup
        }
        val highestValueCup = previousCupByLabel
        val lowestValueCup = initialLinkedCups.first { it.label == 1 }
        highestValueCup.nextCup = initialLinkedCups[0]
        lowestValueCup.destinationCup = highestValueCup

        var currentCup = initialLinkedCups[0]
        for (move in 0 until moves) {
            val floating1 = currentCup.nextCup!!
            val floating2 = floating1.nextCup!!
            val floating3 = floating2.nextCup!!
            var destinationCup = currentCup.destinationCup!!
            while (destinationCup == floating1 || destinationCup == floating2 || destinationCup == floating3) {
                destinationCup = destinationCup.destinationCup!!
            }
            val afterDestination = destinationCup.nextCup
            destinationCup.nextCup = floating1
            currentCup.nextCup = floating3.nextCup
            floating3.nextCup = afterDestination
            currentCup = currentCup.nextCup!!
        }
        val firstPart = lowestValueCup.nextCup!!
        val secondPart = firstPart.nextCup!!
        return firstPart.label.toLong() * secondPart.label.toLong()
    }

    private fun toInitialLinkedCups(initialCups: String): List<LinkedCup> {
        val numberOfCups = initialCups.length
        val linkedCups = initialCups.map { LinkedCup(Character.getNumericValue(it), null, null) }
        var lastCup: LinkedCup? = null
        for (cup in linkedCups.reversed()) {
            cup.nextCup = lastCup
            lastCup = cup
        }
        for (cup in linkedCups) {
            val destinationCup = linkedCups.find { ((it.label + 1) % numberOfCups) == (cup.label % numberOfCups) }
            cup.destinationCup = destinationCup
        }
        return linkedCups
    }

    private data class LinkedCup(val label: Int, var destinationCup: LinkedCup?, var nextCup: LinkedCup?) {
        override fun toString(): String {
            return "label: $label, destinationCup: ${destinationCup?.label}, nextCup: ${nextCup?.label}"
        }
    }
}