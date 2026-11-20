package com.joshuahee.cookbook.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.joshuahee.cookbook.data.Difficulty
import com.joshuahee.cookbook.data.Recipe
import com.joshuahee.cookbook.ui.theme.difficultyColors

/** Shows the recipe's photo, its bundled image, or a placeholder. */
@Composable
fun RecipeImage(recipe: Recipe, modifier: Modifier = Modifier) {
    when {
        recipe.imageUri != null -> AsyncImage(
            model = recipe.imageUri,
            contentDescription = recipe.name,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )

        recipe.imageRes != null -> Image(
            painter = painterResource(recipe.imageRes),
            contentDescription = recipe.name,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )

        else -> Box(
            modifier = modifier.background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text("🍽️", fontSize = 48.sp)
        }
    }
}

@Composable
fun DifficultyBadge(difficulty: Difficulty, modifier: Modifier = Modifier) {
    val (container, content) = difficultyColors(difficulty)
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = container,
        contentColor = content
    ) {
        Text(
            text = difficulty.label,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun InfoPill(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}
