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

    private fun computeScore(cards: LinkedList<Int>): Int {
        return cards.reversed().withIndex().map { (index, value) -> (index + 1) * value }.sum()
    }
}