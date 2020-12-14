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

    @ParameterizedTest
    @CsvSource(
        value = [
            "101010;X1001X;011010,011011,111010,111011",
            "11010;0X0XX;10000,10001,10010,10011,11000,11001,11010,11011",
            "000000000000000000000000000000101010;000000000000000000000000000000X1001X;000000000000000000000000000000011010,000000000000000000000000000000011011,000000000000000000000000000000111010,000000000000000000000000000000111011",
        ], delimiter = ';'
    )
    fun applyMaskV2(value: String, mask: String, expected: String) {
        assertEquals(expected.split(",").toSet(), Day14().applyMaskV2(mask, value).toSet())
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "mask = 000000000000000000000000000000X1001X%" +
                    "mem[42] = 100%" +
                    "mask = 00000000000000000000000000000000X0XX%" +
                    "mem[26] = 1, 208"
        ]
    )
    fun sumOfValuesAfterProgramV2(instructions: String, expected: Long) {
        assertEquals(expected, Day14().sumOfValuesAfterProgramV2(instructions.split("%")))
    }

    @Test
    fun realSumOfValuesAfterProgramV2() {
        val instructions = Files.lines(Paths.get("./src/test/resources/day14.txt")).toList()
        assertEquals(5142195937660, Day14().sumOfValuesAfterProgramV2(instructions))
    }
}