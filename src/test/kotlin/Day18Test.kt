import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths

internal class Day18Test {

    @ParameterizedTest
    @CsvSource(
        "1 + 2 * 3 + 4 * 5 + 6, 71",
        "1 + (2 * 3) + (4 * (5 + 6)), 51",
        "2 * 3 + (4 * 5), 26",
        "5 + (8 * 3 + 9 + 3 * 4 * 3), 437",
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)), 12240",
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2, 13632"
    )
    fun evaluateExpressionLeftPrecedence(expression: String, expected: Long) {
        assertEquals(expected, Day18().evaluateExpressionLeftPrecedence(expression))
    }

    @Test
    fun realEvaluateExpressionLeftPrecedence() {
        val day18 = Day18()
        assertEquals(31142189909908,
            Files.lines(Paths.get("./src/test/resources/day18.txt"))
                .mapToLong { day18.evaluateExpressionLeftPrecedence(it) }.sum()
        )
    }

    @ParameterizedTest
    @CsvSource(
        "1 + 2 * 3 + 4 * 5 + 6, 231",
        "1 + (2 * 3) + (4 * (5 + 6)), 51",
        "2 * 3 + (4 * 5), 46",
        "5 + (8 * 3 + 9 + 3 * 4 * 3), 1445",
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)), 669060",
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2, 23340"
    )
    fun evaluateExpressionPlusPrecedence(expression: String, expected: Long) {
        assertEquals(expected, Day18().evaluateExpressionPlusPrecedence(expression))
    }

    @Test
    fun realEvaluateExpressionPlusPrecedence() {
        val day18 = Day18()
        assertEquals(323912478287549,
            Files.lines(Paths.get("./src/test/resources/day18.txt"))
                .mapToLong { day18.evaluateExpressionPlusPrecedence(it) }.sum()
        )
    }
}