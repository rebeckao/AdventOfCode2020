import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day01Test {

    @ParameterizedTest
    @CsvSource(value = [
        "1721, 979, 366, 299, 675, 1456; 514579",
        "1, 2019; 2019",
        "1010, 0, 1010; 1020100"
    ], delimiter = ';')
    fun productOfTwo2020Parts(rawNumbers: String, expected : Int) {
        val numbers = rawNumbers.split(", ").map{ it.toInt()}
        assertEquals(expected, Day01().productOfTwoParts(numbers, 2020));
    }

    @Test
    fun productOfTwo2020PartsRealInput() {
        val numbers =  Files.lines(Paths.get("./src/test/resources/day01.txt")).mapToInt{it.toInt()}.toList()
        assertEquals(1013211, Day01().productOfTwoParts(numbers, 2020));
    }

    @ParameterizedTest
    @CsvSource(value = [
        "1721, 979, 366, 299, 675, 1456; 241861950",
        "1, 2018, 1; 2018",
        "1010, 0, 1010; 0"
    ], delimiter = ';')
    fun productOfThree2020Parts(rawNumbers: String, expected : Int) {
        val numbers = rawNumbers.split(", ").map{ it.toInt()}
        assertEquals(expected, Day01().productOfThreeParts(numbers, 2020));
    }

    @Test
    fun productOfThree2020PartsRealInput() {
        val numbers =  Files.lines(Paths.get("./src/test/resources/day01.txt")).mapToInt{it.toInt()}.toList()
        assertEquals(13891280, Day01().productOfThreeParts(numbers, 2020));
    }
}