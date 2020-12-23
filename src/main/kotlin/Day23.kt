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
}