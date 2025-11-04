package com.joshuahee.cookbook.ui

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.joshuahee.cookbook.R
import com.joshuahee.cookbook.ui.screens.Recipe

class RecipeViewModel : ViewModel() {

    val recipes = mutableStateListOf(
        // Sample recipes now use null URIs (and we’ll show placeholder)
        Recipe(
            name = "Creamy Garlic Parmesan Pasta",
            imageUri = null,
            description = "Delicious creamy pasta with garlic and parmesan sauce.",
            time = "25 min",
            difficulty = "Easy"
        ),
        Recipe(
            name = "Classic American Burger",
            imageUri = null,
            description = "Juicy grilled burger with melted cheese and fresh toppings.",
            time = "20 min",
            difficulty = "Easy"
        ),
        Recipe(
            name = "Homemade Ramen Bowl",
            imageUri = null,
            description = "Savory ramen noodles with soft-boiled eggs and broth.",
            time = "30 min",
            difficulty = "Medium"
        )
    )

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun getRecipeByName(name: String): Recipe? {
        return recipes.find { it.name == name }
    }
}
