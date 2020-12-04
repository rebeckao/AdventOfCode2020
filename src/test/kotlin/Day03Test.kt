import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day03Test {

    @ParameterizedTest
    @CsvSource(
        value = ["..##.......\\n" +
                "#...#...#..\\n" +
                ".#....#..#.\\n" +
                "..#.#...#.#\\n" +
                ".#...##..#.\\n" +
                "..#.##.....\\n" +
                ".#.#.#....#\\n" +
                ".#........#\\n" +
                "#.##...#...\\n" +
                "#...##....#\\n" +
                ".#..#...#.#,3,1,7"], delimiter = ','
    )
    fun numberOfTreesInPath(rawMap: String, slopeRight: Int, slopeDown: Int, expected: Int) {
        assertEquals(expected, Day03().numberOfTreesInPath(rawMap.split("\\n"), slopeRight, slopeDown))
    }

    @Test
    fun realNumberOfTreesInPath() {
        val map = Files.lines(Paths.get("./src/test/resources/day03.txt")).toList()
        assertEquals(153, Day03().numberOfTreesInPath(map, 3, 1))
    }

    @ParameterizedTest
    @CsvSource(
        value = ["..##.......\\n" +
                "#...#...#..\\n" +
                ".#....#..#.\\n" +
                "..#.#...#.#\\n" +
                ".#...##..#.\\n" +
                "..#.##.....\\n" +
                ".#.#.#....#\\n" +
                ".#........#\\n" +
                "#.##...#...\\n" +
                "#...##....#\\n" +
                ".#..#...#.#,336"], delimiter = ','
    )
    fun numberOfTreesInSeveralPaths(rawMap: String, expected: Int) {
        val day03 = Day03()
        val map = rawMap.split("\\n")
        val numberOfTreesInPaths = day03.numberOfTreesInPath(map, 1, 1) *
                    day03.numberOfTreesInPath(map, 3, 1) *
                    day03.numberOfTreesInPath(map, 5, 1) *
                    day03.numberOfTreesInPath(map, 7, 1) *
                    day03.numberOfTreesInPath(map, 1, 2)
        assertEquals(expected, numberOfTreesInPaths)
    }

    @Test
    fun realNumberOfTreesInSeveralPaths() {
        val map = Files.lines(Paths.get("./src/test/resources/day03.txt")).toList()
        val day03 = Day03()
        val numberOfTreesInPaths = day03.numberOfTreesInPath(map, 1, 1) *
                day03.numberOfTreesInPath(map, 3, 1) *
                day03.numberOfTreesInPath(map, 5, 1) *
                day03.numberOfTreesInPath(map, 7, 1) *
                day03.numberOfTreesInPath(map, 1, 2)
        assertEquals(0, numberOfTreesInPaths)
    }
}