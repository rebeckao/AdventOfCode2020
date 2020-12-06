import java.util.stream.Collectors
import java.util.stream.Stream

class Day06 {
    val delimeter = "<delimeter>"

    fun sumOfYesQuestions(answers: Stream<String>): Int {
        return answers
            .collect(Collectors.joining(delimeter))
            .split(delimeter + delimeter)
            .map { it.replace(delimeter, "") }
            .map { it.toCharArray().toSet().size }
            .sum()
    }
}