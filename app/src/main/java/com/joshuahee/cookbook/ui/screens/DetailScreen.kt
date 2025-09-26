package com.joshuahee.cookbook.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joshuahee.cookbook.data.FakeRepository

@Composable
fun DetailScreen(navController: NavController, id: String) {
    val recipe = FakeRepository.getRecipeById(id)
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(recipe?.title ?: "Recipe", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        Text("Ingredients", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(6.dp))
        recipe?.ingredients?.forEach {
            Text("• ${it.name} – ${it.amount}", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(Modifier.height(12.dp))
        Text("Instructions", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(6.dp))
        Text(recipe?.instructions ?: "-", style = MaterialTheme.typography.bodyMedium)
    }
}
