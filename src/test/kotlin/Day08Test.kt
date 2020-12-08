import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day08Test {

    @Test
    fun accumulatorValueAfterLoop() {
        val instructions = listOf(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6"
        )
        val programResult = Day08().programResult(instructions)
        val programFinished = programResult.first
        val accumulatorValue = programResult.second
        assertFalse(programFinished)
        assertEquals(5, accumulatorValue)
    }

    @Test
    fun realAccumulatorValueAfterLoop() {
        val instructions = Files.lines(Paths.get("./src/test/resources/day08.txt")).toList()
        val (programFinished, accumulatorValue) = Day08().programResult(instructions)
        assertFalse(programFinished)
        assertEquals(1600, accumulatorValue)
    }

    @Test
    fun accumulatorValueAfterFinish() {
        val instructions = listOf(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "nop -4",
            "acc +6"
        )
        val (programFinished, accumulatorValue) = Day08().programResult(instructions)
        assertTrue(programFinished)
        assertEquals(8, accumulatorValue)
    }

    @Test
    fun resultAfterFix() {
        val instructions = listOf(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6"
        )
        val accumulatorValue = Day08().resultAfterFix(instructions)
        assertEquals(8, accumulatorValue)
    }

    @Test
    fun realResultAfterFix() {
        val instructions = Files.lines(Paths.get("./src/test/resources/day08.txt")).toList()
        val accumulatorValue = Day08().resultAfterFix(instructions)
        assertEquals(1543, accumulatorValue)
    }
}