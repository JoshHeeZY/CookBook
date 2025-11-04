package com.joshuahee.cookbook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.joshuahee.cookbook.R
import com.joshuahee.cookbook.ui.RecipeViewModel

@Composable
fun DetailScreen(navController: NavController, recipeName: String, recipeViewModel: RecipeViewModel) {
    val recipe = recipeViewModel.getRecipeByName(recipeName)

    if (recipe != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val painter = if (recipe.imageUri != null)
                rememberAsyncImagePainter(recipe.imageUri)
            else
                painterResource(id = R.drawable.pasta)

            Image(
                painter = painter,
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(recipe.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(12.dp))

            Text("Ingredients", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(6.dp))
            Text(recipe.description, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(12.dp))
            Text("Cooking Time: ${recipe.time}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(6.dp))
            Text("Difficulty: ${recipe.difficulty}", style = MaterialTheme.typography.bodyMedium)
        }
    } else {
        Text("Recipe not found.", modifier = Modifier.padding(16.dp))
    }
}
