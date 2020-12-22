import java.util.*

class Day22 {
    fun scoreAfterGame(player1: List<Int>, player2: List<Int>): Int {
        val player1Cards = LinkedList(player1)
        val player2Cards = LinkedList(player2)
        while (player1Cards.isNotEmpty() && player2Cards.isNotEmpty()) {
            val card1 = player1Cards.pop()
            val card2 = player2Cards.pop()
            if (card1 > card2) {
                player1Cards.addLast(card1)
                player1Cards.addLast(card2)
            } else {
                player2Cards.addLast(card2)
                player2Cards.addLast(card1)
            }
        }
        return computeScore(if (player1Cards.isNotEmpty()) player1Cards else player2Cards)
    }

    fun scoreAfterRecursiveGame(player1: Collection<Int>, player2: Collection<Int>): Pair<Int, Boolean> {
        val player1Cards = LinkedList(player1)
        val player2Cards = LinkedList(player2)
        val previousStates = mutableSetOf<String>()
        while (player1Cards.isNotEmpty() && player2Cards.isNotEmpty()) {
            val currentState: String = toState(player1Cards, player2Cards)
            if (previousStates.contains(currentState)) {
                return Pair(computeScore(player1Cards), true)
            } else {
                previousStates.add(currentState)
            }
            val card1 = player1Cards.pop()
            val card2 = player2Cards.pop()
            if (player1Cards.size >= card1 && player2Cards.size >= card2) {
                if (player1WinsSubGame(card1, player1Cards, card2, player2Cards)) {
                    player1Cards.addLast(card1)
                    player1Cards.addLast(card2)
                } else {
                    player2Cards.addLast(card2)
                    player2Cards.addLast(card1)
                }
            } else {
                if (card1 > card2) {
                    player1Cards.addLast(card1)
                    player1Cards.addLast(card2)
                } else {
                    player2Cards.addLast(card2)
                    player2Cards.addLast(card1)
                }
            }
        }
        val player1Wins = player1Cards.isNotEmpty()
        return Pair(computeScore(if (player1Wins) player1Cards else player2Cards), player1Wins)
    }

    private fun player1WinsSubGame(
        card1: Int,
        player1Cards: LinkedList<Int>,
        card2: Int,
        player2Cards: LinkedList<Int>
    ): Boolean {
        val subDeck1 = player1Cards.subList(0, card1)
        val subDeck2 = player2Cards.subList(0, card2)
        val (_, player1Wins) = scoreAfterRecursiveGame(subDeck1, subDeck2)
        return player1Wins
    }

    private fun toState(player1Cards: LinkedList<Int>, player2Cards: LinkedList<Int>): String {
        return "${player1Cards.joinToString(",")}_${player2Cards.joinToString(",")}"
    }

    private fun computeScore(cards: LinkedList<Int>): Int {
        return cards.reversed().withIndex().map { (index, value) -> (index + 1) * value }.sum()
    }
}