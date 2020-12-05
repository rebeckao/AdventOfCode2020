import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths

internal class Day05Test {

    @ParameterizedTest
    @CsvSource(
        value = [
            "FBFBBFFRLR, 357",
            "BFFFBBFRRR, 567",
            "FFFBBBFRRR, 119",
            "BBFFBBFRLL, 820",
        ]
    )
    fun seatNumber(boardingPass: String, expected: Int) {
        assertEquals(expected, Day05().seatNumber(boardingPass))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "1, 1",
            "0, 0",
            "10, 2",
            "01, 1",
            "1101, 13",
        ]
    )
    fun binaryToDecimal(binary: String, expected: Int) {
        assertEquals(expected, Day05().binaryToDecimal(binary))
    }

    @Test
    fun realHighestSeatNumber() {
        val day05 = Day05()
        val highestSeatNumber = Files.lines(Paths.get("./src/test/resources/day05.txt"))
            .mapToInt { day05.seatNumber(it) }
            .max().orElseThrow()
        assertEquals(822, highestSeatNumber)
    }

    @Test
    fun realMissingSeatNumber() {
        val day05 = Day05()
        val seatNumbers = Files.lines(Paths.get("./src/test/resources/day05.txt"))
            .mapToInt { day05.seatNumber(it) }
            .sorted()
            .toArray()
        assertEquals(705, day05.missingNumber(seatNumbers))
    }
}