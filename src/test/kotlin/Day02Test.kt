import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

internal class Day02Test {

    @ParameterizedTest
    @CsvSource(value = [
        "1-3 a: abcde, true",
        "1-3 b: cdefg, false",
        "2-9 c: ccccccccc, true"
    ])
    fun passwordIsValidAccordingToOccurancePolicy(passwordWithPolicy: String, expected : Boolean) {
        assertEquals(expected, Day02().passwordIsValidAccordingToOccurancePolicy(passwordWithPolicy))
    }

    @Test
    fun numberOfValidPasswordsAccordingToOccurancePolicy() {
        val passwordsWithPolicies =  Files.lines(Paths.get("./src/test/resources/day02.txt")).toList()
        assertEquals(622, Day02().numberOfValidPasswordsAccordingToOccurancePolicy(passwordsWithPolicies))
    }

    @ParameterizedTest
    @CsvSource(value = [
        "1-3 a: abcde, true",
        "1-3 b: cdefg, false",
        "2-9 c: ccccccccc, false"
    ])
    fun passwordIsValidAccordingToIndexPolicy(passwordWithPolicy: String, expected : Boolean) {
        assertEquals(expected, Day02().passwordIsValidAccordingToIndexPolicy(passwordWithPolicy))
    }

    @Test
    fun numberOfValidPasswordsAccordingToIndexPolicy() {
        val passwordsWithPolicies =  Files.lines(Paths.get("./src/test/resources/day02.txt")).toList()
        assertEquals(263, Day02().numberOfValidPasswordsAccordingToIndexPolicy(passwordsWithPolicies))
    }
}