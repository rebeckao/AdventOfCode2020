class Day02 {
    val pattern = Regex("(\\d+)-(\\d+) (\\w): (.*)")

    fun numberOfValidPasswordsAccordingToOccurancePolicy(passwordsWithPolicies : List<String>) : Int {
        return passwordsWithPolicies.filter { passwordIsValidAccordingToOccurancePolicy(it) }.count()
    }

    fun passwordIsValidAccordingToOccurancePolicy(passwordWithPolicy : String) : Boolean {
        val matchedGroups = pattern.matchEntire(passwordWithPolicy)?.groupValues ?: throw IllegalArgumentException()
        val minOccurences = matchedGroups[1].toInt()
        val maxOccurences = matchedGroups[2].toInt()
        val character = matchedGroups[3][0]
        val password = matchedGroups[4]

        val occurences = password.toCharArray().filter { it == character }.count()
        return occurences >= minOccurences && occurences <= maxOccurences
    }

    fun numberOfValidPasswordsAccordingToIndexPolicy(passwordsWithPolicies : List<String>) : Int {
        return passwordsWithPolicies.filter { passwordIsValidAccordingToIndexPolicy(it) }.count()
    }

    fun passwordIsValidAccordingToIndexPolicy(passwordWithPolicy : String) : Boolean {
        val matchedGroups = pattern.matchEntire(passwordWithPolicy)?.groupValues ?: throw IllegalArgumentException()
        val firstIndex = matchedGroups[1].toInt()
        val secondIndex = matchedGroups[2].toInt()
        val character = matchedGroups[3][0]
        val password = matchedGroups[4]

        val firstMatches = password[firstIndex - 1] == character
        val secondMatches = password[secondIndex - 1] == character
        return firstMatches != secondMatches
    }
}