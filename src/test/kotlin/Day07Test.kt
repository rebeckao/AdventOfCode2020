import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day07Test {

    @ParameterizedTest
    @CsvSource(
        "shiny gold, 4",
        "dark orange, 0",
        "faded blue, 7"
    )
    fun numberOfColorsThatCanContain(targetColor: String, expected: Int) {
        val rules = listOf(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags."
        )
        assertEquals(expected, Day07().numberOfColorsThatCanContain(rules, targetColor))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "bright gray bags contain 5 pale aqua bags, 3 shiny gold bags, 1 clear olive bag, 1 dull fuchsia bag.; 1",
            "bright gray bags contain 5 pale aqua bags, 3 shiny gold bags, 1 clear olive bag, 1 dull fuchsia bag.#" +
                    "pale salmon bags contain 5 bright gray bags.; 2"],
        delimiter = ';'
    )
    fun numberOfColorsThatCanContainShinyGold(rawRules: String, expected: Int) {
        val rules = rawRules.split("#")
        assertEquals(expected, Day07().numberOfColorsThatCanContain(rules, "shiny gold"))
    }

    @Test
    fun realNumberOfColorsThatCanContain() {
        val rules = Files.lines(Paths.get("./src/test/resources/day07.txt")).toList()
        assertEquals(229, Day07().numberOfColorsThatCanContain(rules, "shiny gold"))
    }


    @ParameterizedTest
    @CsvSource(
        value = [
            "light red bags contain 1 bright white bag, 2 muted yellow bags.#" +
                    "dark orange bags contain 3 bright white bags, 4 muted yellow bags.#" +
                    "bright white bags contain 1 shiny gold bag.#" +
                    "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.#" +
                    "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.#" +
                    "dark olive bags contain 3 faded blue bags, 4 dotted black bags.#" +
                    "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.#" +
                    "faded blue bags contain no other bags.#" +
                    "dotted black bags contain no other bags.; 32",
            "shiny gold bags contain 2 dark red bags.#" +
                    "dark red bags contain 2 dark orange bags.#" +
                    "dark orange bags contain 2 dark yellow bags.#" +
                    "dark yellow bags contain 2 dark green bags.#" +
                    "dark green bags contain 2 dark blue bags.#" +
                    "dark blue bags contain 2 dark violet bags.#" +
                    "dark violet bags contain no other bags.; 126"],
        delimiter = ';'
    )
    fun numberOfBagsInside(rawRules: String, expected: Int) {
        val rules = rawRules.split("#")
        assertEquals(expected, Day07().numberOfBagsInside(rules, "shiny gold"))
    }

    @Test
    fun realNumberOfBagsInside() {
        val rules = Files.lines(Paths.get("./src/test/resources/day07.txt")).toList()
        assertEquals(6683, Day07().numberOfBagsInside(rules, "shiny gold"))
    }
}