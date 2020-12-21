class Day21 {
    private val foodPattern = Regex("(.*) \\(contains (.*)\\)")

    fun occurrencesOfSafeIngredients(foods: List<String>): Int {
        val ingredientsPerAllergen = mutableMapOf<String, Set<String>>()
        val allIngredientOccurrences = mutableListOf<String>()
        for (food in foods) {
            val (ingredients, allergens) = parseFood(food)
            for (allergen in allergens) {
                ingredientsPerAllergen[allergen] = ingredientsPerAllergen
                    .getOrDefault(allergen, ingredients)
                    .filter { ingredients.contains(it) }
                    .toSet()
            }
            allIngredientOccurrences.addAll(ingredients)
        }
        val unsafeFoods = ingredientsPerAllergen.values.flatten()
        return allIngredientOccurrences.filter { !unsafeFoods.contains(it) }.count()
    }

    fun allergenIngredients(foods: List<String>): String {
        val ingredientsPerAllergen = mutableMapOf<String, MutableSet<String>>()
        for (food in foods) {
            val (ingredients, allergens) = parseFood(food)
            for (allergen in allergens) {
                ingredientsPerAllergen[allergen] = ingredientsPerAllergen
                    .getOrDefault(allergen, ingredients)
                    .filter { ingredients.contains(it) }
                    .toMutableSet()
            }
        }

        while (ingredientsPerAllergen.values.map { it.size }.any { it > 1 }) {
            val alreadyIdentifiedIngredients = ingredientsPerAllergen.values.filter { it.size == 1 }.map { it.first() }
            for (possibleIngredients in ingredientsPerAllergen.values.filter { it.size > 1 }) {
                possibleIngredients.removeAll(alreadyIdentifiedIngredients)
            }
        }

        return ingredientsPerAllergen.entries
            .sortedBy { it.key }.joinToString(",") { it.value.first() }
    }

    private fun parseFood(food: String): Pair<Set<String>, List<String>> {
        val groupValues = foodPattern.matchEntire(food)!!.groupValues
        val ingredients = groupValues[1].split(" ").toSet()
        val allergens = groupValues[2].split(", ")
        return Pair(ingredients, allergens)
    }
}