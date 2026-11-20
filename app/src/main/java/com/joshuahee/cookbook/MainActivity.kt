package com.joshuahee.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
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

    // One RecipeViewModel shared by every screen
    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            CookBookTheme {
                CookBookApp(recipeViewModel)
            }
        }
    }
}

private object Routes {
    const val HOME = "home"
    const val ADD_RECIPE = "addRecipe"
    const val RECIPE_ID = "recipeId"
    const val DETAIL = "detail/{$RECIPE_ID}"
    fun detail(recipeId: String) = "detail/$recipeId"
}

@Composable
fun CookBookApp(viewModel: RecipeViewModel) {
    val navController = rememberNavController()

    // Each screen owns its Scaffold, so there is no outer Scaffold here.
    NavHost(navController = navController, startDestination = Routes.HOME) {

        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onRecipeClick = { id -> navController.navigate(Routes.detail(id)) },
                onAddRecipeClick = { navController.navigate(Routes.ADD_RECIPE) }
            )
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument(Routes.RECIPE_ID) { type = NavType.StringType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString(Routes.RECIPE_ID).orEmpty()
            DetailScreen(
                recipe = viewModel.getRecipe(recipeId),
                onBack = { navController.popBackStack() },
                onDelete = {
                    navController.popBackStack()
                    viewModel.deleteRecipe(recipeId)
                }
            )
        }

        composable(Routes.ADD_RECIPE) {
            AddRecipeScreen(
                onBack = { navController.popBackStack() },
                onSave = { recipe ->
                    viewModel.addRecipe(recipe)
                    navController.popBackStack()
                }
            )
        }
    }
}
