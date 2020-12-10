import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day10Test {

    @ParameterizedTest
    @CsvSource(
        value = ["16,10,15,5,1,11,7,19,6,12,4; 35",
            "28,33,18,42,31,14,46,20,48,47,24,23,49,45,19,38,39,11,1,32,25,35,8,17,7,9,4,2,34,10,3; 220"],
        delimiter = ';'
    )
    fun joltageDistributions(rawAdapters: String, expected: Int) {
        val adapters = rawAdapters.split(",").map { it.toInt() }
        val (oneJoltageDifferences, threeJoltageDifferences) = Day10().joltageDistributions(adapters)
        assertEquals(expected, oneJoltageDifferences * threeJoltageDifferences)
    }


    @Test
    fun realJoltageDistributions() {
        val adapters = Files.lines(Paths.get("./src/test/resources/day10.txt")).map { it.toInt() }.toList()
        val (oneJoltageDifferences, threeJoltageDifferences) = Day10().joltageDistributions(adapters)
        assertEquals(2080, oneJoltageDifferences * threeJoltageDifferences)
    }
}