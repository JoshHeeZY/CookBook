package com.joshuahee.cookbook.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.joshuahee.cookbook.data.RecipeRepo

@Composable
fun DetailScreen(navController: NavController, backStackEntry: NavBackStackEntry) {
    val recipeId = backStackEntry.arguments?.getString("id")
    val recipe = recipeId?.let { RecipeRepo.byId(it) }

    Column(modifier = Modifier.fillMaxSize()) {
        if (recipe != null) {
            Text(text = recipe.title)
            Text(text = recipe.instructions)
        } else {
            Text(text = "Recipe not found")
        }
    }
}
