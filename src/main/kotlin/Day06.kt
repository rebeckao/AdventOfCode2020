import java.util.stream.Collectors
import java.util.stream.Collectors.groupingBy
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

    fun sumOfConsensusYesQuestions(answers: Stream<String>): Int {
        return answers
            .collect(Collectors.joining(delimeter))
            .split(delimeter + delimeter)
            .map { it.split(delimeter) }
            .map { numberOfCharactersOccuringOnEveryLine(it) }
            .sum()
    }

    private fun numberOfCharactersOccuringOnEveryLine(lines: List<String>): Int {
        val numberOfLines = lines.size
        return lines.joinToString()
            .chars()
            .boxed()
            .collect(groupingBy { it })
            .filter { it.value.size == numberOfLines }
            .count()

    }
}