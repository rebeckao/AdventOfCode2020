import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day23Test {

    @ParameterizedTest
    @CsvSource(
        "389125467,10,92658374",
        "389125467,100,67384529",
        "712643589,100,29385746"
    )
    fun crabCups(initialCups: String, moves: Int, expected: String) {
        assertEquals(expected, Day23().crabCups(initialCups, moves))
    }
}