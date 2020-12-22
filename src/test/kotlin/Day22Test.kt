import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day22Test {

    @Test
    fun scoreAfterGame() {
        val player1 = listOf(9, 2, 6, 3, 1)
        val player2 = listOf(5, 8, 4, 7, 10)
        assertEquals(306, Day22().scoreAfterGame(player1, player2))
    }

    @Test
    fun realScoreAfterGame() {
        val player1 = Files.lines(Paths.get("./src/test/resources/day22_player1.txt")).map { it.toInt() }.toList()
        val player2 = Files.lines(Paths.get("./src/test/resources/day22_player2.txt")).map { it.toInt() }.toList()
        assertEquals(32102, Day22().scoreAfterGame(player1, player2))
    }
}