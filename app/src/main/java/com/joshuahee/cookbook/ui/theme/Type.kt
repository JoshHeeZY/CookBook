package com.joshuahee.cookbook.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontWeight

private val Default = Typography()

val Typography = Typography(
    headlineMedium = Default.headlineMedium.copy(fontWeight = FontWeight.Bold),
    headlineSmall = Default.headlineSmall.copy(fontWeight = FontWeight.Bold),
    titleLarge = Default.titleLarge.copy(fontWeight = FontWeight.SemiBold),
    titleMedium = Default.titleMedium.copy(fontWeight = FontWeight.SemiBold),
    labelLarge = Default.labelLarge.copy(fontWeight = FontWeight.SemiBold)
)
