package com.joshuahee.cookbook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joshuahee.cookbook.R

@Composable
fun DetailScreen(navController: NavController, recipeName: String) {
    val recipe = when (recipeName) {
        "Creamy Garlic Parmesan Pasta" -> Recipe(
            "Creamy Garlic Parmesan Pasta",
            R.drawable.pasta,
            "Delicious creamy pasta with garlic and parmesan sauce.",
            "25 min",
            "Easy"
        )
        "Classic American Burger" -> Recipe(
            "Classic American Burger",
            R.drawable.burger,
            "Juicy grilled burger with melted cheese and fresh toppings.",
            "20 min",
            "Easy"
        )
        "Homemade Ramen Bowl" -> Recipe(
            "Homemade Ramen Bowl",
            R.drawable.ramen,
            "Savory ramen noodles with soft-boiled eggs and broth.",
            "30 min",
            "Medium"
        )
        else -> null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (recipe != null) {
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(recipe.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(recipe.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Cooking time: ${recipe.time}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Difficulty: ${recipe.difficulty}", style = MaterialTheme.typography.bodyMedium)
        } else {
            Text("Recipe not found.", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
