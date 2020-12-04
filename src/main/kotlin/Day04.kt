class Day04 {
    val requiredCredentials = setOf(
        "byr",
        "iyr",
        "eyr",
        "hgt",
        "hcl",
        "ecl",
        "pid"
    )

    fun passportIsValid(passport: String): Boolean {
        val availableCredentials = passport.split("[\\s\\r\\n]+".toRegex())
            .map { it.split(":") }
            .map { it[0] }
            .toSet()
        return availableCredentials.containsAll(requiredCredentials)
    }
}