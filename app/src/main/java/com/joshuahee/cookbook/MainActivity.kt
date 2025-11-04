package com.joshuahee.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joshuahee.cookbook.ui.RecipeViewModel
import com.joshuahee.cookbook.ui.screens.AddRecipeScreen
import com.joshuahee.cookbook.ui.screens.DetailScreen
import com.joshuahee.cookbook.ui.screens.HomeScreen
import com.joshuahee.cookbook.ui.theme.CookBookTheme

class MainActivity : ComponentActivity() {

    // Create a single instance of RecipeViewModel shared across screens
    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookBookTheme {
                AppRoot(recipeViewModel)
            }
        }
    }
}

@Composable
fun AppRoot(recipeViewModel: RecipeViewModel) {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            // 🏠 Home Screen
            composable("home") {
                HomeScreen(
                    navController = navController,
                    recipeViewModel = recipeViewModel
                )
            }

            // 📄 Detail Screen
            composable(
                route = "detail/{name}",
                arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("name") ?: ""
                DetailScreen(
                    navController = navController,
                    recipeName = name,
                    recipeViewModel = recipeViewModel
                )
            }

            // ➕ Add Recipe Screen
            composable("addRecipe") {
                AddRecipeScreen(
                    navController = navController,
                    recipeViewModel = recipeViewModel
                )
            }
        }
    }
}
