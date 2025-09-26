package com.joshuahee.cookbook.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joshuahee.cookbook.data.Category
import com.joshuahee.cookbook.data.FakeRepository

@Composable
fun HomeScreen(navController: NavController) {
    val categories = FakeRepository.categories

    Surface(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { cat ->
                CategoryTile(
                    category = cat,
                    onClick = {
                        // For now, just open the first recipe as a demo
                        val first = FakeRepository.recipes.firstOrNull() ?: return@CategoryTile
                        navController.navigate("detail/${first.id}")
                    }
                )
            }
        }
    }
}

@Composable
private fun CategoryTile(
    category: Category,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .aspectRatio(1f)               // square tiles
            .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        // (Optional) You can add an AsyncImage here if you add Coil.
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer, shape = MaterialTheme.shapes.small),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "img", style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = category.count.toString(),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
