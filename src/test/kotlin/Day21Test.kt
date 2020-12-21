import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day21Test {

    @ParameterizedTest
    @CsvSource(
        value = [
            "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)%" +
                    "trh fvjkl sbzzf mxmxvkd (contains dairy)%" +
                    "sqjhc fvjkl (contains soy)%" +
                    "sqjhc mxmxvkd sbzzf (contains fish);5"
        ], delimiter = ';'
    )
    fun occurancesOfSafeIngredients(rawFoods: String, expected: Int) {
        assertEquals(expected, Day21().occurancesOfSafeIngredients(rawFoods.split("%")))
    }

    @Test
    fun realOccurancesOfSafeIngredients() {
        val foods = Files.lines(Paths.get("./src/test/resources/day21.txt")).toList()
        assertEquals(1945, Day21().occurancesOfSafeIngredients(foods))
    }
}