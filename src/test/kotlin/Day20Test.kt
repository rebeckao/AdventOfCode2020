import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

internal class Day20Test {
    private val rawTestTiles = listOf(
        listOf(
            "Tile 2311:",
            "..##.#..#.",
            "##..#.....",
            "#...##..#.",
            "####.#...#",
            "##.##.###.",
            "##...#.###",
            ".#.#.#..##",
            "..#....#..",
            "###...#.#.",
            "..###..###"
        ), listOf(
            "Tile 1951:",
            "#.##...##.",
            "#.####...#",
            ".....#..##",
            "#...######",
            ".##.#....#",
            ".###.#####",
            "###.##.##.",
            ".###....#.",
            "..#.#..#.#",
            "#...##.#.."
        ), listOf(
            "Tile 1171:",
            "####...##.",
            "#..##.#..#",
            "##.#..#.#.",
            ".###.####.",
            "..###.####",
            ".##....##.",
            ".#...####.",
            "#.##.####.",
            "####..#...",
            ".....##..."
        ), listOf(
            "Tile 1427:",
            "###.##.#..",
            ".#..#.##..",
            ".#.##.#..#",
            "#.#.#.##.#",
            "....#...##",
            "...##..##.",
            "...#.#####",
            ".#.####.#.",
            "..#..###.#",
            "..##.#..#."
        ), listOf(
            "Tile 1489:",
            "##.#.#....",
            "..##...#..",
            ".##..##...",
            "..#...#...",
            "#####...#.",
            "#..#.#.#.#",
            "...#.#.#..",
            "##.#...##.",
            "..##.##.##",
            "###.##.#.."
        ), listOf(
            "Tile 2473:",
            "#....####.",
            "#..#.##...",
            "#.##..#...",
            "######.#.#",
            ".#...#.#.#",
            ".#########",
            ".###.#..#.",
            "########.#",
            "##...##.#.",
            "..###.#.#."
        ), listOf(
            "Tile 2971:",
            "..#.#....#",
            "#...###...",
            "#.#.###...",
            "##.##..#..",
            ".#####..##",
            ".#..####.#",
            "#..#.#..#.",
            "..####.###",
            "..#.#.###.",
            "...#.#.#.#"
        ), listOf(
            "Tile 2729:",
            "...#.#.#.#",
            "####.#....",
            "..#.#.....",
            "....#..#.#",
            ".##..##.#.",
            ".#.####...",
            "####.#.#..",
            "##.####...",
            "##..#.##..",
            "#.##...##."
        ), listOf(
            "Tile 3079:",
            "#.#.#####.",
            ".#..######",
            "..#.......",
            "######....",
            "####.#..#.",
            ".#...#.##.",
            "#.#####.##",
            "..#.###...",
            "..#.......",
            "..#.###..."
        )
    )

    @Test
    fun productOfCornerTileIds() {
        assertEquals(20899048083289, Day20().productOfCornerTileIds(rawTestTiles))
    }

    @Test
    fun realProductOfCornerTileIds() {
        val rawTiles = tilesFromFile()
        assertEquals(29125888761511, Day20().productOfCornerTileIds(rawTiles))
    }

    @Test
    fun toCompleteMap() {
        val expected = listOf(
            ".#.#..#.##...#.##..#####",
            "###....#.#....#..#......",
            "##.##.###.#.#..######...",
            "###.#####...#.#####.#..#",
            "##.#....#.##.####...#.##",
            "...########.#....#####.#",
            "....#..#...##..#.#.###..",
            ".####...#..#.....#......",
            "#..#.##..#..###.#.##....",
            "#.####..#.####.#.#.###..",
            "###.#.#...#.######.#..##",
            "#.####....##..########.#",
            "##..##.#...#...#.#.#.#..",
            "...#..#..#.#.##..###.###",
            ".#.#....#.##.#...###.##.",
            "###.#...#..#.##.######..",
            ".#.#.###.##.##.#..#.##..",
            ".####.###.#...###.#..#.#",
            "..#.#..#..#.#.#.####.###",
            "#..####...#.#.#.###.###.",
            "#####..#####...###....##",
            "#.##..#..#...#..####...#",
            ".#.###..##..##..####.##.",
            "...###...##...#...#..###"
        )

        val actual = Day20().toCompleteMap(rawTestTiles)
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource(
        "ab;" +
                "cd," +
                "1," +
                "ca;" +
                "db",
        "ab;" +
                "cd," +
                "2," +
                "dc;" +
                "ba",
        "ab;" +
                "cd," +
                "3," +
                "bd;" +
                "ac",
    )
    fun rotateClockwise(toRotate: String, times: Int, expected: String) {
        assertEquals(expected.split(";"), Day20().rotateClockwise(toRotate.split(";"), times))
    }

    @ParameterizedTest
    @CsvSource(
        ".####...#####..#...###..;" +
                "#####..#..#.#.####..#.#.;" +
                ".#.#...#.###...#.##.##..;" +
                "#.#.##.###.#.##.##.#####;" +
                "..##.###.####..#.####.##;" +
                "...#.#..##.##...#..#..##;" +
                "#.##.#..#.#..#..##.#.#..;" +
                ".###.##.....#...###.#...," +

                ".####...#####..#...###..;" +
                "#####..#..#.#.####..#.#.;" +
                ".#.#...#.###...#.##.O#..;" +
                "#.O.##.OO#.#.OO.##.OOO##;" +
                "..#O.#O#.O##O..O.#O##.##;" +
                "...#.#..##.##...#..#..##;" +
                "#.##.#..#.#..#..##.#.#..;" +
                ".###.##.....#...###.#...",

        ".####...#####..#...###.;" +
                "#####..#..#.#.####..#.#;" +
                ".#.#...#.###...#.##.##.;" +
                "#.#.##.###.#.##.##.####;" +
                "..##.###.####..#.####.#;" +
                "...#.#..##.##...#..#..#;" +
                "#.##.#..#.#..#..##.#.#.;" +
                ".###.##.....#...###.#..," +

                ".####...#####..#...###.;" +
                "#####..#..#.#.####..#.#;" +
                ".#.#...#.###...#.##.O#.;" +
                "#.O.##.OO#.#.OO.##.OOO#;" +
                "..#O.#O#.O##O..O.#O##.#;" +
                "...#.#..##.##...#..#..#;" +
                "#.##.#..#.#..#..##.#.#.;" +
                ".###.##.....#...###.#..",

        ".####...#####..#...###;" +
                "#####..#..#.#.####..#.;" +
                ".#.#...#.###...#.##.##;" +
                "#.#.##.###.#.##.##.###;" +
                "..##.###.####..#.####.;" +
                "...#.#..##.##...#..#..;" +
                "#.##.#..#.#..#..##.#.#;" +
                ".###.##.....#...###.#.," +

                ".####...#####..#...###;" +
                "#####..#..#.#.####..#.;" +
                ".#.#...#.###...#.##.O#;" +
                "#.O.##.OO#.#.OO.##.OOO;" +
                "..#O.#O#.O##O..O.#O##.;" +
                "...#.#..##.##...#..#..;" +
                "#.##.#..#.#..#..##.#.#;" +
                ".###.##.....#...###.#.",

        ".#...#.###...#.##.##;" +
                "#.##.###.#.##.##.###;" +
                "##.###.####..#.####.," +

                ".#...#.###...#.##.O#;" +
                "O.##.OO#.#.OO.##.OOO;" +
                "#O.#O#.O##O..O.#O##."
    )
    fun markSeaMonsters(map: String, expected: String) {
        assertEquals(expected.split(";"), Day20().markSeaMonsters(map.split(";")))
    }

    @ParameterizedTest
    @CsvSource(
        ".##....#.#....#......;" +
                "###.#..##.#.###..#.##;" +
                "..###.#.##..#..##.#..," +

                ".O#....#.#....#......;" +
                "OOO.#..OO.#.#OO..#.O#;" +
                "..#O#.O.#O..O..O#.O..",

        ".##....#.#....#.....;" +
                "###.#..##.#.###..#.#;" +
                "..###.#.##..#..##.#.," +

                ".O#....#.#....#.....;" +
                "OOO.#..OO.#.#OO..#.O;" +
                "..#O#.O.#O..O..O#.O."
    )
    fun markSeaMonstersInAllDirections(map: String, expected: String) {
        val actual = Day20().markSeaMonstersInAllDirections(map.split(";"))
        assertEquals(expected.split(";"), actual)
    }

    @Test
    fun markSeaMonstersInAllDirectionsForTestTiles() {
        val day20 = Day20()
        val expected = listOf(
            ".####...#####..#...###..",
            "#####..#..#.#.####..#.#.",
            ".#.#...#.###...#.##.O#..",
            "#.O.##.OO#.#.OO.##.OOO##",
            "..#O.#O#.O##O..O.#O##.##",
            "...#.#..##.##...#..#..##",
            "#.##.#..#.#..#..##.#.#..",
            ".###.##.....#...###.#...",
            "#.####.#.#....##.#..#.#.",
            "##...#..#....#..#...####",
            "..#.##...###..#.#####..#",
            "....#.##.#.#####....#...",
            "..##.##.###.....#.##..#.",
            "#...#...###..####....##.",
            ".#.##...#.##.#.#.###...#",
            "#.###.#..####...##..#...",
            "#.###...#.##...#.##O###.",
            ".O##.#OO.###OO##..OOO##.",
            "..O#.O..O..O.#O##O##.###",
            "#.#..##.########..#..##.",
            "#.#####..#.#...##..#....",
            "#....##..#.#########..##",
            "#...#.....#..##...###.##",
            "#..###....##.#...##.##.#"
        )
        val map = day20.toCompleteMap(rawTestTiles)
        val actual = day20.rotateClockwise(day20.flipLeftToRight(day20.markSeaMonstersInAllDirections(map)), 3)
        assertEquals(expected, actual)
    }

    @Test
    fun waterRoughness() {
        assertEquals(273, Day20().waterRoughness(rawTestTiles))
    }

    @Test
    fun realWaterRoughness() {
        assertEquals(2219, Day20().waterRoughness(tilesFromFile()))
    }

    private fun tilesFromFile(): List<List<String>> {
        val delimiter = "<delimeter>"
        return Files.lines(Paths.get("./src/test/resources/day20.txt"))
            .collect(Collectors.joining(delimiter))
            .split("$delimiter$delimiter")
            .map { it.split(delimiter).filter { str -> str.isNotBlank() } }
    }
}