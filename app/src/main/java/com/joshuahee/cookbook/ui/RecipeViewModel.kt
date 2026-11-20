package com.joshuahee.cookbook.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.joshuahee.cookbook.data.Difficulty
import com.joshuahee.cookbook.data.Recipe
import com.joshuahee.cookbook.data.SampleRecipes

class RecipeViewModel : ViewModel() {

    private val _recipes = mutableStateListOf<Recipe>().apply { addAll(SampleRecipes.all) }
    val recipes: List<Recipe> get() = _recipes

    var searchQuery by mutableStateOf("")
        private set

    var difficultyFilter by mutableStateOf<Difficulty?>(null)
        private set

    /** Recipes matching the current search text and difficulty filter. */
    val visibleRecipes: List<Recipe>
        get() {
            val query = searchQuery.trim()
            return _recipes.filter { recipe ->
                (difficultyFilter == null || recipe.difficulty == difficultyFilter) &&
                    (query.isEmpty() ||
                        recipe.name.contains(query, ignoreCase = true) ||
                        recipe.description.contains(query, ignoreCase = true) ||
                        recipe.ingredients.any { it.contains(query, ignoreCase = true) })
            }
        }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
    }

    fun onDifficultyFilterChange(difficulty: Difficulty?) {
        difficultyFilter = difficulty
    }

    fun addRecipe(recipe: Recipe) {
        _recipes.add(0, recipe)
    }

    fun deleteRecipe(id: String) {
        _recipes.removeAll { it.id == id }
    }

    fun getRecipe(id: String): Recipe? = _recipes.find { it.id == id }
}
