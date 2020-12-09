import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day09Test {

    @Test
    fun firstNumberNotMatching() {
        val numbers = listOf(
            35,
            20,
            15,
            25,
            47,
            40,
            62,
            55,
            65,
            95,
            102,
            117,
            150,
            182,
            127,
            219,
            299,
            277,
            309,
            576
        ).map { it.toLong() }
        assertEquals(127, Day09().firstNumberNotMatching(numbers, 5))
    }

    @Test
    fun realFirstNumberNotMatching() {
        val numbers = Files.lines(Paths.get("./src/test/resources/day09.txt")).map { it.toLong() }.toList()
        assertEquals(20874512, Day09().firstNumberNotMatching(numbers, 25))
    }

    @Test
    fun contiguousSetSummingTo() {
        val numbers = listOf(
            35,
            20,
            15,
            25,
            47,
            40,
            62,
            55,
            65,
            95,
            102,
            117,
            150,
            182,
            127,
            219,
            299,
            277,
            309,
            576
        ).map { it.toLong() }
        assertEquals(62, Day09().contiguousSetSummingTo(127, numbers))
    }

    @Test
    fun realContiguousSetSummingTo() {
        val numbers = Files.lines(Paths.get("./src/test/resources/day09.txt")).map { it.toLong() }.toList()
        assertEquals(3012420, Day09().contiguousSetSummingTo(20874512, numbers))
    }
}