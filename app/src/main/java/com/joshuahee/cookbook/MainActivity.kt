package com.joshuahee.cookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joshuahee.cookbook.ui.screens.DetailScreen
import com.joshuahee.cookbook.ui.screens.HomeScreen
import com.joshuahee.cookbook.ui.theme.CookBookTheme

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CookBookTheme { AppRoot() } }
    }
}

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    Scaffold { inner ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(inner)
        ) {
            composable("home") { HomeScreen(navController) }
            composable(
                route = "detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { backStack ->
                val id = backStack.arguments?.getString("id") ?: ""
                DetailScreen(navController, id)
            }
        }
    }
}
