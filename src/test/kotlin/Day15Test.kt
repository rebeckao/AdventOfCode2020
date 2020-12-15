import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day15Test {

    @ParameterizedTest
    @CsvSource(
        value = [
            "0,3,6;10;0",
            "0,3,6;2020;436",
            "1,3,2;2020;1",
            "2,1,3;2020;10",
            "1,2,3;2020;27",
            "2,3,1;2020;78",
            "3,2,1;2020;438",
            "3,1,2;2020;1836",
            "7,14,0,17,11,1,2;2020;206",
            "0,3,6;30000000;175594",
            "1,3,2;30000000;2578",
            "2,1,3;30000000;3544142",
            "1,2,3;30000000;261214",
            "2,3,1;30000000;6895259",
            "3,2,1;30000000;18",
            "3,1,2;30000000;362",
            "7,14,0,17,11,1,2;30000000;955",
        ], delimiter = ';'
    )
    fun numberAfterTurns(startingNumbers: String, endTurn: Int, expected: Int) {
        assertEquals(expected, Day15().numberAfterTurns(startingNumbers.split(",").map { it.toInt() }, endTurn))
    }
}