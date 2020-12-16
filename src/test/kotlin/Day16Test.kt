
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day16Test {

    @Test
    fun errorScanningRate() {
        val rules = listOf(
            "class: 1-3 or 5-7",
            "row: 6-11 or 33-44",
            "seat: 13-40 or 45-50"
        )
        val tickets = listOf(
            "7,3,47",
            "40,4,50",
            "55,2,20",
            "38,6,12"
        )
        assertEquals(71, Day16().errorScanningRate(rules, tickets))
    }

    @Test
    fun realErrorScanningRate() {
        val rules = Files.lines(Paths.get("./src/test/resources/day16_rules.txt")).toList()
        val tickets = Files.lines(Paths.get("./src/test/resources/day16_nearby_tickets.txt")).toList()
        assertEquals(25895, Day16().errorScanningRate(rules, tickets))
    }
}