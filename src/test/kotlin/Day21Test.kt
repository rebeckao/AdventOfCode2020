import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day21Test {

    @Test
    fun occurencesOfSafeIngredients() {
        val foods = listOf(
            "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
            "trh fvjkl sbzzf mxmxvkd (contains dairy)",
            "sqjhc fvjkl (contains soy)",
            "sqjhc mxmxvkd sbzzf (contains fish)"
        )
        assertEquals(5, Day21().occurrencesOfSafeIngredients(foods))
    }

    @Test
    fun realOccurencesOfSafeIngredients() {
        val foods = Files.lines(Paths.get("./src/test/resources/day21.txt")).toList()
        assertEquals(1945, Day21().occurrencesOfSafeIngredients(foods))
    }

    @Test
    fun allergenIngredients() {
        val foods = listOf(
            "mxmxvkd kfcds sqjhc nhms (contains dairy, fish)",
            "trh fvjkl sbzzf mxmxvkd (contains dairy)",
            "sqjhc fvjkl (contains soy)",
            "sqjhc mxmxvkd sbzzf (contains fish)"
        )
        assertEquals("mxmxvkd,sqjhc,fvjkl", Day21().allergenIngredients(foods))
    }

    @Test
    fun realAllergenIngredients() {
        val foods = Files.lines(Paths.get("./src/test/resources/day21.txt")).toList()
        assertEquals("pgnpx,srmsh,ksdgk,dskjpq,nvbrx,khqsk,zbkbgp,xzb", Day21().allergenIngredients(foods))
    }
}