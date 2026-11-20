@file:OptIn(ExperimentalMaterial3Api::class)

package com.joshuahee.cookbook.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.joshuahee.cookbook.data.Recipe
import com.joshuahee.cookbook.data.formatCookTime
import com.joshuahee.cookbook.ui.components.DifficultyBadge
import com.joshuahee.cookbook.ui.components.InfoPill
import com.joshuahee.cookbook.ui.components.RecipeImage

@Composable
fun DetailScreen(
    recipe: Recipe?,
    onBack: () -> Unit,
    onDelete: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = recipe?.name.orEmpty(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    if (recipe != null) {
                        IconButton(onClick = { showDeleteDialog = true }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete recipe")
                        }
                    }
                }
            )
        }
    ) { padding ->
        if (recipe == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("Recipe not found.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            RecipeDetailContent(recipe = recipe, contentPadding = padding)
        }
    }

    if (showDeleteDialog && recipe != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete recipe?") },
            text = { Text("\"${recipe.name}\" will be removed from your CookBook.") },
            confirmButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    onDelete()
                }) { Text("Delete") }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) { Text("Cancel") }
            }
        )
    }
}

@Composable
private fun RecipeDetailContent(recipe: Recipe, contentPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding(),
            bottom = contentPadding.calculateBottomPadding() + 32.dp
        )
    ) {
        item {
            RecipeImage(
                recipe = recipe,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(24.dp))
            )
        }

        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(recipe.name, style = MaterialTheme.typography.headlineSmall)
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    InfoPill("⏱ ${formatCookTime(recipe.cookTimeMinutes)}")
                    DifficultyBadge(recipe.difficulty)
                }
                if (recipe.description.isNotBlank()) {
                    Spacer(Modifier.height(16.dp))
                    Text(
                        recipe.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item { SectionHeader("Ingredients") }
        if (recipe.ingredients.isEmpty()) {
            item { EmptySectionText("No ingredients added.") }
        } else {
            itemsIndexed(recipe.ingredients) { _, ingredient ->
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(ingredient, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        item { SectionHeader("Steps") }
        if (recipe.steps.isEmpty()) {
            item { EmptySectionText("No steps added.") }
        } else {
            itemsIndexed(recipe.steps) { index, step ->
                Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "${index + 1}",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(
                        step,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
    )
}

@Composable
private fun EmptySectionText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}
