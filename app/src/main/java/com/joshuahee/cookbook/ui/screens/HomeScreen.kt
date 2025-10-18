package com.joshuahee.cookbook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.joshuahee.cookbook.R

// ✅ Recipe data model
data class Recipe(
    val name: String,
    val imageRes: Int,
    val description: String,
    val time: String,
    val difficulty: String
)

// ✅ Home screen showing recipe list
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val sampleRecipes = listOf(
        Recipe(
            "Creamy Garlic Parmesan Pasta",
            R.drawable.pasta,
            "Delicious creamy pasta with garlic and parmesan sauce.",
            "25 min",
            "Easy"
        ),
        Recipe(
            "Classic American Burger",
            R.drawable.burger,
            "Juicy grilled burger with melted cheese and fresh toppings.",
            "20 min",
            "Easy"
        ),
        Recipe(
            "Homemade Ramen Bowl",
            R.drawable.ramen,
            "Savory ramen noodles with soft-boiled eggs and broth.",
            "30 min",
            "Medium"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("CookBook", fontWeight = FontWeight.Bold, fontSize = 22.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF9ECCC))
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(sampleRecipes) { recipe ->
                RecipeCard(recipe) {
                    navController.navigate("detail/${recipe.name}")
                }
            }
        }
    }
}

// ✅ Card component for each recipe
@Composable
fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(recipe.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(recipe.difficulty, color = Color(0xFF4CAF50), fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(recipe.description, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(recipe.time, fontSize = 12.sp, color = Color(0xFF757575))
            }
        }
    }
}
