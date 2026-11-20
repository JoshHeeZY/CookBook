@file:OptIn(ExperimentalMaterial3Api::class)

package com.joshuahee.cookbook.ui.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.joshuahee.cookbook.data.Difficulty
import com.joshuahee.cookbook.data.Recipe
import java.io.File

@Composable
fun AddRecipeScreen(
    onBack: () -> Unit,
    onSave: (Recipe) -> Unit
) {
    val context = LocalContext.current

    var name by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var cookTime by rememberSaveable { mutableStateOf("") }
    var difficulty by rememberSaveable { mutableStateOf(Difficulty.Easy) }
    var ingredientsText by rememberSaveable { mutableStateOf("") }
    var stepsText by rememberSaveable { mutableStateOf("") }
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var pendingCameraUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var showErrors by rememberSaveable { mutableStateOf(false) }

    val cookTimeMinutes = cookTime.toIntOrNull()
    val nameError = showErrors && name.isBlank()
    val timeError = showErrors && (cookTimeMinutes == null || cookTimeMinutes <= 0)

    // System photo picker: no storage permission needed.
    val photoPicker = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri -> if (uri != null) imageUri = uri }

    // Full-resolution camera photo saved through our FileProvider.
    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success -> if (success) imageUri = pendingCameraUri }

    fun pickPhoto() = photoPicker.launch(
        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
    )

    fun takePhoto() {
        val uri = createImageUri(context)
        pendingCameraUri = uri
        cameraLauncher.launch(uri)
    }

    fun save() {
        if (name.isBlank() || cookTimeMinutes == null || cookTimeMinutes <= 0) {
            showErrors = true
            return
        }
        onSave(
            Recipe(
                name = name.trim(),
                description = description.trim(),
                cookTimeMinutes = cookTimeMinutes,
                difficulty = difficulty,
                ingredients = ingredientsText.toLines(),
                steps = stepsText.toLines(),
                imageUri = imageUri
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New recipe") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Close, contentDescription = "Discard")
                    }
                },
                actions = {
                    TextButton(onClick = { save() }) { Text("Save") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Photo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { pickPhoto() },
                contentAlignment = Alignment.Center
            ) {
                if (imageUri != null) {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = "Recipe photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            "Add a photo",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { pickPhoto() }, modifier = Modifier.weight(1f)) {
                    Text("📁  Gallery")
                }
                OutlinedButton(onClick = { takePhoto() }, modifier = Modifier.weight(1f)) {
                    Text("📷  Camera")
                }
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Recipe name *") },
                isError = nameError,
                supportingText = if (nameError) {
                    { Text("Please give your recipe a name") }
                } else null,
                singleLine = true,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Short description") },
                minLines = 2,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cookTime,
                onValueChange = { input -> cookTime = input.filter(Char::isDigit).take(4) },
                label = { Text("Cooking time *") },
                trailingIcon = { Text("min", modifier = Modifier.padding(end = 12.dp)) },
                isError = timeError,
                supportingText = if (timeError) {
                    { Text("Enter the time in minutes") }
                } else null,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Column {
                Text("Difficulty", style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Difficulty.values().forEach { option ->
                        FilterChip(
                            selected = difficulty == option,
                            onClick = { difficulty = option },
                            label = { Text(option.label) }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = ingredientsText,
                onValueChange = { ingredientsText = it },
                label = { Text("Ingredients") },
                placeholder = { Text("One per line, e.g.\n2 eggs\n1 cup flour") },
                minLines = 4,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = stepsText,
                onValueChange = { stepsText = it },
                label = { Text("Steps") },
                placeholder = { Text("One step per line") },
                minLines = 4,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = { save() }, modifier = Modifier.fillMaxWidth()) {
                Text("Save recipe")
            }
        }
    }
}

/** Splits multi-line input into clean list items, dropping bullets and numbering. */
internal fun String.toLines(): List<String> =
    lines()
        .map { it.trim().trimStart('-', '•', '*', ' ').replace(Regex("^\\d+[.)]\\s*"), "").trim() }
        .filter { it.isNotEmpty() }

private fun createImageUri(context: Context): Uri {
    val dir = File(context.filesDir, "recipe_images").apply { mkdirs() }
    val file = File(dir, "recipe_${System.currentTimeMillis()}.jpg")
    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
}
