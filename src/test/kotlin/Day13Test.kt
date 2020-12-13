import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day13Test {

    @Test
    fun earliestDeparture() {
        val buses = "7,13,x,x,59,x,31,19"
        assertEquals(295, Day13().earliestDeparture(939, buses))
    }

    @Test
    fun realEarliestDeparture() {
        val input = Files.lines(Paths.get("./src/test/resources/day13.txt")).toList()
        assertEquals(136, Day13().earliestDeparture(input[0].toInt(), input[1]))
    }
}