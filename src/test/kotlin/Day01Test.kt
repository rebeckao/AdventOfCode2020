import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day01Test {

    @ParameterizedTest
    @CsvSource("0, 1")
    fun firstTask(input: Int, expected : Int) {
        assertEquals(expected, Day01().firstTask(input));
    }
}