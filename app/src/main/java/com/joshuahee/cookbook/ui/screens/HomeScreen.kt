@file:OptIn(ExperimentalMaterial3Api::class)

package com.joshuahee.cookbook.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.joshuahee.cookbook.data.Difficulty
import com.joshuahee.cookbook.data.Recipe
import com.joshuahee.cookbook.data.formatCookTime
import com.joshuahee.cookbook.ui.RecipeViewModel
import com.joshuahee.cookbook.ui.components.DifficultyBadge
import com.joshuahee.cookbook.ui.components.RecipeImage

@Composable
fun HomeScreen(
    viewModel: RecipeViewModel,
    onRecipeClick: (String) -> Unit,
    onAddRecipeClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val recipes = viewModel.visibleRecipes

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { Text("CookBook") },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddRecipeClick,
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("New recipe") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = padding.calculateTopPadding(),
                // Leave room so the FAB never covers the last card
                bottom = padding.calculateBottomPadding() + 96.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SearchField(
                    query = viewModel.searchQuery,
                    onQueryChange = viewModel::onSearchQueryChange
                )
            }
            item {
                DifficultyFilterRow(
                    selected = viewModel.difficultyFilter,
                    onSelect = viewModel::onDifficultyFilterChange
                )
            }

            if (recipes.isEmpty()) {
                item {
                    EmptyState(
                        isFiltering = viewModel.searchQuery.isNotBlank() || viewModel.difficultyFilter != null
                    )
                }
            } else {
                items(recipes, key = { it.id }) { recipe ->
                    RecipeCard(recipe = recipe, onClick = { onRecipeClick(recipe.id) })
                }
            }
        }
    }
}

@Composable
private fun SearchField(query: String, onQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Search recipes or ingredients") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear search")
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(28.dp)
    )
}

@Composable
private fun DifficultyFilterRow(selected: Difficulty?, onSelect: (Difficulty?) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            FilterChip(
                selected = selected == null,
                onClick = { onSelect(null) },
                label = { Text("All") }
            )
        }
        items(Difficulty.values().toList()) { difficulty ->
            FilterChip(
                selected = selected == difficulty,
                onClick = { onSelect(if (selected == difficulty) null else difficulty) },
                label = { Text(difficulty.label) }
            )
        }
    }
}

@Composable
private fun RecipeCard(recipe: Recipe, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        RecipeImage(
            recipe = recipe,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Spacer(Modifier.width(8.dp))
                DifficultyBadge(recipe.difficulty)
            }
            if (recipe.description.isNotBlank()) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = buildString {
                    append("⏱ ").append(formatCookTime(recipe.cookTimeMinutes))
                    if (recipe.ingredients.isNotEmpty()) {
                        append("  ·  ${recipe.ingredients.size} ingredients")
                    }
                },
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun EmptyState(isFiltering: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🥄", style = MaterialTheme.typography.displayMedium)
        Spacer(Modifier.height(12.dp))
        Text(
            text = if (isFiltering) "No recipes match your search" else "No recipes yet",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = if (isFiltering) "Try a different word or filter." else "Tap \"New recipe\" to add your first one.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
