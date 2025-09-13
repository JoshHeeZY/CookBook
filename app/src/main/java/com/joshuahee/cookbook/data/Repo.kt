package com.joshuahee.cookbook.data

object RecipeRepo {
    private val recipes = listOf(
        Recipe(
            id = "1",
            title = "Spaghetti Bolognese",
            imageUrl = null,
            instructions = "Cook pasta, brown meat, add sauce.",
            ingredients = listOf(
                Ingredient("Spaghetti", "200g"),
                Ingredient("Minced Beef", "300g"),
                Ingredient("Tomato Sauce", "1 cup")
            )
        ),
        Recipe(
            id = "2",
            title = "Grilled Cheese",
            imageUrl = null,
            instructions = "Butter bread, add cheese, grill until golden.",
            ingredients = listOf(
                Ingredient("Bread", "2 slices"),
                Ingredient("Cheese", "2 slices"),
                Ingredient("Butter", "1 tbsp")
            )
        )
    )

    fun all(): List<Recipe> = recipes
    fun byId(id: String): Recipe? = recipes.find { it.id == id }
}
