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

    @ParameterizedTest
    @CsvSource(
        value = [
            "byr:2002, true",
            "byr:2003, false",
            "hgt:60in, true",
            "hgt:190cm, true",
            "hgt:190in, false",
            "hgt:190, false",
            "hcl:#123abc, true",
            "hcl:#123abz, false",
            "hcl:123abc, false",
            "ecl:brn, true",
            "ecl:wat, false",
            "pid:000000001, true",
            "pid:0123456789, false"
        ]
    )
    fun credentialIsValid(rawCredential: String, expected: Boolean) {
        val credential = rawCredential.split(":")
        assertEquals(expected, Day04().credentialIsValid(credential[0], credential[1]))
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "eyr:1972 cid:100\\n" +
                    "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926, false",
            "iyr:2019\\n" +
                    "hcl:#602927 eyr:1967 hgt:170cm\\n" +
                    "ecl:grn pid:012533040 byr:1946, false",
            "hcl:dab227 iyr:2012\\n" +
                    "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277, false",
            "hgt:59cm ecl:zzz\\n" +
                    "eyr:2038 hcl:74454a iyr:2023\\n" +
                    "pid:3556412378 byr:2007, false",
            "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\\n" +
                    "hcl:#623a2f, true",
            "eyr:2029 ecl:blu cid:129 byr:1989\\n" +
                    "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm, true",
            "hcl:#888785\\n" +
                    "hgt:164cm byr:2001 iyr:2015 cid:88\\n" +
                    "pid:545766238 ecl:hzl\\n" +
                    "eyr:2022, true",
            "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719, true"
        ]
    )
    fun passportIsReallyValid(credentials: String, expected: Boolean) {
        assertEquals(expected, Day04().passportIsReallyValid(credentials.replace("\\n", "\n")))
    }

    @Test
    fun realNumberOfReallyValidPassports() {
        val day04 = Day04()
        val validPassports = Files.lines(Paths.get("./src/test/resources/day04.txt"))
            .collect(joining("<delimeter>"))
            .split("<delimeter><delimeter>")
            .map { it.replace("<delimeter>", "\n") }
            .filter { day04.passportIsReallyValid(it) }
            .count()
        assertEquals(208, validPassports)
    }
}