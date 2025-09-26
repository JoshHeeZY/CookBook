package com.joshuahee.cookbook.data

data class Recipe(
    val id: String,
    val title: String,
    val imageUrl: String? = null,
    val instructions: String,
    val ingredients: List<Ingredient>
)

data class Ingredient(
    val name: String,
    val amount: String
)

data class Category(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val count: Int = 0
)
