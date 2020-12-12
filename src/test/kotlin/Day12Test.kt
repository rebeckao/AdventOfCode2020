import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day12Test {

    @Test
    fun manhattanDistanceToTarget() {
        val instructions = listOf(
            "F10",
            "N3",
            "F7",
            "R90",
            "F11"
        )
        assertEquals(25, Day12().manhattanDistanceToTarget(instructions))
    }

    @Test
    fun realManhattanDistanceToTarget() {
        val instructions = Files.lines(Paths.get("./src/test/resources/day12.txt")).toList()
        assertEquals(420, Day12().manhattanDistanceToTarget(instructions))
    }
}