import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger
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

    @ParameterizedTest
    @CsvSource(
        value = [
            "7,13,x,x,59,x,31,19;1068781",
            "17,x,13,19;3417",
            "67,7,59,61;754018",
            "67,x,7,59,61;779210",
            "67,7,x,59,61;1261476",
            "1789,37,47,1889;1202161486"
        ], delimiter = ';'
    )
    fun earliestTimestampForConsecutiveDepartures(rawBuses: String, expected: Long) {
        assertEquals(BigInteger.valueOf(expected), Day13().earliestTimestampForConsecutiveDepartures(rawBuses))
    }

    @Test
    fun realEarliestTimestampForConsecutiveDepartures() {
        val input = Files.lines(Paths.get("./src/test/resources/day13.txt")).toList()
        assertEquals(BigInteger("305068317272992"), Day13().earliestTimestampForConsecutiveDepartures(input[1]))
    }

    @ParameterizedTest
    @CsvSource(
        "3,2",
        "33,20",
        "48, 16",
        "92, 44"
    )
    fun eulerPhi(n: Long, expected: Int) {
        assertEquals(expected, Day13().eulerPhi(n))
    }

    @ParameterizedTest
    @CsvSource(
        "2,3,210,140",
        "3,7,210,360",
        "3,10,210,63",
        "0,10,210,0"
    )
    fun chineseRemainderTheoremPartForSingleEquation(a: Long, n: Long, N: Long, expected: Long) {
        assertEquals(
            BigInteger.valueOf(expected),
            Day13().chineseRemainderTheoremPartForSingleEquation(a, n, N)
        )
    }

    @ParameterizedTest
    @CsvSource(
        "2,3,70,1",
        "6,7,30,4",
        "4,10,21,1"
    )
    fun b(eulerPhi: Int, n: Long, productOfOthers: Long, expected: Long) {
        assertEquals(expected, Day13().b(productOfOthers, eulerPhi, n))
    }
}