import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day11Test {

    @ParameterizedTest
    @CsvSource(
        value = [
            "L.LL.LL.LL%" +
                    "LLLLLLL.LL%" +
                    "L.L.L..L..%" +
                    "LLLL.LL.LL%" +
                    "L.LL.LL.LL%" +
                    "L.LLLLL.LL%" +
                    "..L.L.....%" +
                    "LLLLLLLLLL%" +
                    "L.LLLLLL.L%" +
                    "L.LLLLL.LL, 37"
        ]
    )
    fun occupiedSeatsAfterConvergence(rawSeats: String, expected: Int) {
        assertEquals(expected, Day11().occupiedSeatsAfterConvergence(rawSeats.split("%")))
    }

    @Test
    fun realOccupiedSeatsAfterConvergence() {
        val seats = Files.lines(Paths.get("./src/test/resources/day11.txt")).toList()
        assertEquals(2316, Day11().occupiedSeatsAfterConvergence(seats))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "L.LL.LL.LL%" +
                    "LLLLLLL.LL%" +
                    "L.L.L..L..%" +
                    "LLLL.LL.LL%" +
                    "L.LL.LL.LL%" +
                    "L.LLLLL.LL%" +
                    "..L.L.....%" +
                    "LLLLLLLLLL%" +
                    "L.LLLLLL.L%" +
                    "L.LLLLL.LL, 26"
        ]
    )
    fun occupiedSeatsAfterConvergenceWithVisibility(rawSeats: String, expected: Int) {
        assertEquals(expected, Day11().occupiedSeatsAfterConvergenceWithVisibility(rawSeats.split("%")))
    }

    @Test
    fun realOccupiedSeatsAfterConvergenceWithVisibility() {
        val seats = Files.lines(Paths.get("./src/test/resources/day11.txt")).toList()
        assertEquals(2128, Day11().occupiedSeatsAfterConvergenceWithVisibility(seats))
    }
}