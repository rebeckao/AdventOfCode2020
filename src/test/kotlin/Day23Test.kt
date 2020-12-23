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

    @ParameterizedTest
    @CsvSource(
        "389125467,10,10,42",
        "389125467,1000000,10000000,149245887792",
        "712643589,1000000,10000000,680435423892"
    )
    fun manyCrabCups(initialCups: String, numberOfCups: Int, moves: Int, expected: Long) {
        assertEquals(expected, Day23().manyCrabCups(initialCups, numberOfCups, moves))
    }
}