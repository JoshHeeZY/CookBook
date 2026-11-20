package com.joshuahee.cookbook.data

import android.net.Uri
import androidx.annotation.DrawableRes
import java.util.UUID

enum class Difficulty(val label: String) {
    Easy("Easy"),
    Medium("Medium"),
    Hard("Hard")
}

data class Recipe(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val cookTimeMinutes: Int,
    val difficulty: Difficulty,
    val ingredients: List<String> = emptyList(),
    val steps: List<String> = emptyList(),
    /** Photo picked from the gallery or taken with the camera. */
    val imageUri: Uri? = null,
    /** Bundled image used by the sample recipes. */
    @DrawableRes val imageRes: Int? = null
)

/** Formats minutes as "25 min", "1 h" or "1 h 15 min". */
fun formatCookTime(minutes: Int): String {
    val hours = minutes / 60
    val mins = minutes % 60
    return when {
        hours == 0 -> "$mins min"
        mins == 0 -> "$hours h"
        else -> "$hours h $mins min"
    }
}
