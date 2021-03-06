import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day24Test {

    @ParameterizedTest
    @CsvSource(
        "esew, 1",
        "nwwswee, 1",
        "sesenwnenenewseeswwswswwnenewsewsw%" +
                "neeenesenwnwwswnenewnwwsewnenwseswesw%" +
                "seswneswswsenwwnwse%" +
                "nwnwneseeswswnenewneswwnewseswneseene%" +
                "swweswneswnenwsewnwneneseenw%" +
                "eesenwseswswnenwswnwnwsewwnwsene%" +
                "sewnenenenesenwsewnenwwwse%" +
                "wenwwweseeeweswwwnwwe%" +
                "wsweesenenewnwwnwsenewsenwwsesesenwne%" +
                "neeswseenwwswnwswswnw%" +
                "nenwswwsewswnenenewsenwsenwnesesenew%" +
                "enewnwewneswsewnwswenweswnenwsenwsw%" +
                "sweneswneswneneenwnewenewwneswswnese%" +
                "swwesenesewenwneswnwwneseswwne%" +
                "enesenwswwswneneswsenwnewswseenwsese%" +
                "wnwnesenesenenwwnenwsewesewsesesew%" +
                "nenewswnwewswnenesenwnesewesw%" +
                "eneswnwswnwsenenwnwnwwseeswneewsenese%" +
                "neswnwewnwnwseenwseesewsenwsweewe%" +
                "wseweeenwnesenwwwswnew, 10"
    )
    fun blackTiles(tileReferences: String, expected: Int) {
        assertEquals(expected, Day24().blackTiles(tileReferences.split("%")))
    }

    @Test
    fun realBlackTiles() {
        assertEquals(0, Day24().blackTiles(Files.lines(Paths.get("./src/test/resources/day24.txt")).toList()))
    }

    @ParameterizedTest
    @CsvSource(
        "0, 10",
        "1, 15",
        "5, 23",
        "20, 132",
        "100, 2208",
    )
    fun blackTilesAfterDays(days: Int, expected: Int) {
        val tileReferences = listOf(
            "sesenwnenenewseeswwswswwnenewsewsw",
            "neeenesenwnwwswnenewnwwsewnenwseswesw",
            "seswneswswsenwwnwse",
            "nwnwneseeswswnenewneswwnewseswneseene",
            "swweswneswnenwsewnwneneseenw",
            "eesenwseswswnenwswnwnwsewwnwsene",
            "sewnenenenesenwsewnenwwwse",
            "wenwwweseeeweswwwnwwe",
            "wsweesenenewnwwnwsenewsenwwsesesenwne",
            "neeswseenwwswnwswswnw",
            "nenwswwsewswnenenewsenwsenwnesesenew",
            "enewnwewneswsewnwswenweswnenwsenwsw",
            "sweneswneswneneenwnewenewwneswswnese",
            "swwesenesewenwneswnwwneseswwne",
            "enesenwswwswneneswsenwnewswseenwsese",
            "wnwnesenesenenwwnenwsewesewsesesew",
            "nenewswnwewswnenesenwnesewesw",
            "eneswnwswnwsenenwnwnwwseeswneewsenese",
            "neswnwewnwnwseenwseesewsenwsweewe",
            "wseweeenwnesenwwwswnew"
        )
        assertEquals(expected, Day24().blackTilesAfterDays(tileReferences, days))
    }

    @Test
    fun realBlackTilesAfterDays() {
        assertEquals(
            4214,
            Day24().blackTilesAfterDays(Files.lines(Paths.get("./src/test/resources/day24.txt")).toList(), 100)
        )
    }
}