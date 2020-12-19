import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day19Test {

    @ParameterizedTest
    @CsvSource(
        "0: 1 2;" +
                "1: \"a\";" +
                "2: 1 3 | 3 1;" +
                "3: \"b\", ^a((ab)|(ba))$",
        "0: 4 1 5;" +
                "1: 2 3 | 3 2;" +
                "2: 4 4 | 5 5;" +
                "3: 4 5 | 5 4;" +
                "4: \"a\";" +
                "5: \"b\", ^a((((aa)|(bb))((ab)|(ba)))|(((ab)|(ba))((aa)|(bb))))b$"
    )
    fun rulesToRegex(rawRules: String, expected: String) {
        assertEquals(expected, Day19().rulesToRegex(rawRules.split(";")))
    }

    @Test
    fun realNumberOfMessagesThatMatch() {
        val rules = Files.lines(Paths.get("./src/test/resources/day19_rules.txt")).toList()
        val regex = Regex(Day19().rulesToRegex(rules))
        assertEquals(
            187,
            Files.lines(Paths.get("./src/test/resources/day19_messages.txt")).filter { regex.matches(it) }.count()
        )
    }
}