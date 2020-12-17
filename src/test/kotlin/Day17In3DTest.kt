import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day17In3DTest {

    @Test
    fun cubesAfterCycles() {
        val initialCubes = listOf(
            ".#.",
            "..#",
            "###"
        )
        assertEquals(112, Day17in3D().cubesAfterCycles(6, initialCubes))
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
        assertEquals(333, Day17in3D().cubesAfterCycles(6, initialCubes))
    }
}