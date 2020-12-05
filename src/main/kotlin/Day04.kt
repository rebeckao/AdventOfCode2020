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

    fun passportIsReallyValid(passport: String) : Boolean {
        val availableCredentials = passport.split("[\\s\\r\\n]+".toRegex())
            .map { it.split(":") }
            .filter { credentialIsValid(it[0], it[1]) }
            .map { it[0] }
            .toSet()
        return availableCredentials.containsAll(requiredCredentials)
    }

    fun credentialIsValid(type: String, value: String): Boolean {
        return when (type) {
            "byr" -> value.matches("\\d{4}".toRegex()) && value.toInt() >= 1920 && value.toInt() <= 2002
            "iyr" -> value.matches("\\d{4}".toRegex()) && value.toInt() >= 2010 && value.toInt() <= 2020
            "eyr" -> value.matches("\\d{4}".toRegex()) && value.toInt() >= 2020 && value.toInt() <= 2030
            "hgt" -> {
                if (value.matches("\\d+cm".toRegex())) {
                    val cm = value.substring(0, value.length - 2).toInt()
                    return cm >= 150 && cm <= 193
                } else if (value.matches("\\d+in".toRegex())) {
                    val inches = value.substring(0, value.length - 2).toInt()
                    return inches >= 59 && inches <= 76
                } else {
                    return false
                }
            }
            "hcl" -> value.matches("#[0-9a-f]{6}".toRegex())
            "ecl" -> value.matches("(amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth)".toRegex())
            "pid" -> value.matches("[0-9]{9}".toRegex())
            else -> true
        }
    }
}