import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day17Test {

    @Test
    fun cubesAfterCycles() {
        val initialCubes = listOf(
            ".#.",
            "..#",
            "###"
        )
        assertEquals(112, Day17().cubesAfterCycles(6, initialCubes))
    }

    @Test
    fun realCubesAfterCycles() {
        val initialCubes = listOf(
            "....###.",
            "#...####",
            "##.#.###",
            "..#.#...",
            "##.#.#.#",
            "#.######",
            "..#..#.#",
            "######.#"
        )
        assertEquals(333, Day17().cubesAfterCycles(6, initialCubes))
    }
}