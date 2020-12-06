import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

internal class Day06Test {

    @Test
    fun sumOfYesQuestions() {
        val answers = Files.lines(Paths.get("./src/test/resources/day06_1.txt"))
        assertEquals(11, Day06().sumOfYesQuestions(answers))
    }

    @Test
    fun realSumOfYesQuestions() {
        val answers = Files.lines(Paths.get("./src/test/resources/day06.txt"))
        assertEquals(6532, Day06().sumOfYesQuestions(answers))
    }

    @Test
    fun sumOfConsensusYesQuestions() {
        val answers = Files.lines(Paths.get("./src/test/resources/day06_1.txt"))
        assertEquals(6, Day06().sumOfConsensusYesQuestions(answers))
    }

    @Test
    fun realSumOfConsensusYesQuestions() {
        val answers = Files.lines(Paths.get("./src/test/resources/day06.txt"))
        assertEquals(3427, Day06().sumOfConsensusYesQuestions(answers))
    }
}