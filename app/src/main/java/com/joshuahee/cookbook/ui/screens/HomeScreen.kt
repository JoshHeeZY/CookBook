package com.joshuahee.cookbook.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.joshuahee.cookbook.data.RecipeRepo

@Composable
fun HomeScreen(navController: NavController) {
    val recipes = RecipeRepo.all()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(recipes) { recipe ->
            Column(
                modifier = Modifier
                    .clickable {
                        navController.navigate("detail/${recipe.id}")
                    }
            ) {
                Text(text = recipe.title)
            }
        }
    }
}
