import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
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

    @ParameterizedTest
    @CsvSource(
        value = [
            "class: 1-3 or 5-7%" +
                    "row: 6-11 or 33-44%" +
                    "seat: 13-40 or 45-50;" +
                    "7,3,47%" +
                    "40,4,50%" +
                    "55,2,20%" +
                    "38,6,12; row,class,seat",
            "class: 0-1 or 4-19%" +
                    "row: 0-5 or 8-19%" +
                    "seat: 0-13 or 16-19;" +
                    "3,9,18%" +
                    "15,1,5%" +
                    "5,14,9; row,class,seat"
        ], delimiter = ';'
    )
    fun identifiedFields(rawRules: String, rawTickets: String, rawExpected: String) {
        val rules = rawRules.split("%")
        val tickets = rawTickets.split("%")
        val expected = rawExpected.split(",")
        assertEquals(expected, Day16().identifiedFields(rules, tickets))
    }

    @Test
    fun realDepartureFields() {
        val rules = Files.lines(Paths.get("./src/test/resources/day16_rules.txt")).toList()
        val tickets = Files.lines(Paths.get("./src/test/resources/day16_nearby_tickets.txt")).toList()
        val rawTicket = "61,151,137,191,59,163,89,83,71,179,67,149,197,167,181,173,53,139,193,157"
        assertEquals(5865723727753, Day16().departureFields(rules, tickets, rawTicket))
    }
}