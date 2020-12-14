import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day14Test {

    @ParameterizedTest
    @CsvSource(
        value = [
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X%" +
                    "mem[8] = 11%" +
                    "mem[7] = 101%" +
                    "mem[8] = 0, 165"
        ]
    )
    fun sumOfValuesAfterProgram(instructions: String, expected: Long) {
        assertEquals(expected, Day14().sumOfValuesAfterProgram(instructions.split("%")))
    }

    @Test
    fun realSumOfValuesAfterProgram() {
        val instructions = Files.lines(Paths.get("./src/test/resources/day14.txt")).toList()
        assertEquals(11501064782628, Day14().sumOfValuesAfterProgram(instructions))
    }
}