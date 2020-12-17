import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day17In4DTest {

    @Test
    fun cubesAfterCycles() {
        val initialCubes = listOf(
            ".#.",
            "..#",
            "###"
        )
        assertEquals(848, Day17in4D().cubesAfterCycles(6, initialCubes))
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
        assertEquals(2676, Day17in4D().cubesAfterCycles(6, initialCubes))
    }
}