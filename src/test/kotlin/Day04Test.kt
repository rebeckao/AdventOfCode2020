import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors.joining

internal class Day04Test {

    @ParameterizedTest
    @CsvSource(
        value = [
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\\n" +
                    "byr:1937 iyr:2017 cid:147 hgt:183cm, true",
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\\n" +
                    "hcl:#cfa07d byr:1929, false",
            "hcl:#ae17e1 iyr:2013\\n" +
                    "eyr:2024\\n" +
                    "ecl:brn pid:760753108 byr:1931\\n" +
                    "hgt:179cm, true",
            "hcl:#cfa07d eyr:2025 pid:166559648\\n" +
                    "iyr:2011 ecl:brn hgt:59in, false"
        ]
    )
    fun passportIsValid(credentials: String, expected: Boolean) {
        assertEquals(expected, Day04().passportIsValid(credentials.replace("\\n", "\n")))
    }

    @Test
    fun numberOfValidPassports() {
        val day04 = Day04()
        val validPassports = Files.lines(Paths.get("./src/test/resources/day04_1.txt"))
            .collect(joining("<delimeter>"))
            .split("<delimeter><delimeter>")
            .map { it.replace("<delimeter>", "\n") }
            .filter { day04.passportIsValid(it) }
            .count()
        assertEquals(2, validPassports)
    }

    @Test
    fun realNumberOfValidPassports() {
        val day04 = Day04()
        val validPassports = Files.lines(Paths.get("./src/test/resources/day04.txt"))
            .collect(joining("<delimeter>"))
            .split("<delimeter><delimeter>")
            .map { it.replace("<delimeter>", "\n") }
            .filter { day04.passportIsValid(it) }
            .count()
        assertEquals(208, validPassports)
    }
}