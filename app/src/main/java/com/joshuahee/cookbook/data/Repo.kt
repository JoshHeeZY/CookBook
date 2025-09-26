package com.joshuahee.cookbook.data

object FakeRepository {

    val categories = listOf(
        Category("c1", "Breakfast", count = 12),
        Category("c2", "Dessert",   count = 33),
        Category("c3", "Lunch",     count = 20),
        Category("c4", "Snack",     count = 6),
        Category("c5", "Main Dish", count = 167),
        Category("c6", "Beverage",  count = 8),
        Category("c7", "Side Dish", count = 40),
        Category("c8", "All",       count = 282)
    )

    val recipes = listOf(
        Recipe(
            id = "101",
            title = "Grilled Cheese",
            imageUrl = null,
            instructions = "1) Butter bread\n2) Add cheese\n3) Grill until golden.",
            ingredients = listOf(
                Ingredient("Bread", "2 slices"),
                Ingredient("Cheese", "2 slices"),
                Ingredient("Butter", "1 tbsp")
            )
        ),
        Recipe(
            id = "102",
            title = "Pancakes",
            imageUrl = null,
            instructions = "Mix, pour, flip, serve.",
            ingredients = listOf(
                Ingredient("Flour", "1 cup"),
                Ingredient("Milk", "3/4 cup"),
                Ingredient("Egg", "1")
            )
        )
    )

    fun getRecipeById(id: String): Recipe? = recipes.find { it.id == id }
}
