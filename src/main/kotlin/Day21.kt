class Day21 {
    private val foodPattern = Regex("(.*) \\(contains (.*)\\)")

    fun occurancesOfSafeIngredients(foods: List<String>): Int {
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
        val unsafeFoods = ingredientsPerAllergen.values.flatMap { it }
        return allIngredientOccurrences.filter { !unsafeFoods.contains(it) }.count()
    }

    private fun parseFood(food: String): Pair<Set<String>, List<String>> {
        val groupValues = foodPattern.matchEntire(food)!!.groupValues
        val ingredients = groupValues[1].split(" ").toSet()
        val allergens = groupValues[2].split(", ")
        return Pair(ingredients, allergens)
    }
}