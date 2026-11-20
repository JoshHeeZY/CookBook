package com.joshuahee.cookbook.data

import com.joshuahee.cookbook.R

object SampleRecipes {

    val all = listOf(
        Recipe(
            id = "sample-pasta",
            name = "Creamy Garlic Parmesan Pasta",
            description = "Silky pasta tossed in a rich garlic and parmesan cream sauce.",
            cookTimeMinutes = 25,
            difficulty = Difficulty.Easy,
            imageRes = R.drawable.pasta,
            ingredients = listOf(
                "250 g fettuccine",
                "3 cloves garlic, minced",
                "200 ml heavy cream",
                "60 g parmesan, grated",
                "2 tbsp butter",
                "Salt, pepper and parsley"
            ),
            steps = listOf(
                "Cook the pasta in salted water until al dente. Save a cup of pasta water.",
                "Melt the butter in a pan and gently fry the garlic for 1 minute.",
                "Add the cream and simmer for 3 minutes, then stir in the parmesan.",
                "Toss the pasta in the sauce, loosening with pasta water if needed.",
                "Season and finish with chopped parsley."
            )
        ),
        Recipe(
            id = "sample-burger",
            name = "Classic American Burger",
            description = "Juicy grilled burger with melted cheese and fresh toppings.",
            cookTimeMinutes = 20,
            difficulty = Difficulty.Easy,
            imageRes = R.drawable.burger,
            ingredients = listOf(
                "500 g ground beef",
                "4 burger buns",
                "4 slices cheddar",
                "Lettuce, tomato and onion",
                "Salt and pepper"
            ),
            steps = listOf(
                "Shape the beef into 4 patties and season both sides.",
                "Grill or pan-fry for 3–4 minutes per side.",
                "Add the cheese in the last minute and cover to melt.",
                "Toast the buns and build the burgers with your toppings."
            )
        ),
        Recipe(
            id = "sample-ramen",
            name = "Homemade Ramen Bowl",
            description = "Savory ramen noodles in a rich broth with soft-boiled eggs.",
            cookTimeMinutes = 45,
            difficulty = Difficulty.Medium,
            imageRes = R.drawable.ramen,
            ingredients = listOf(
                "2 portions ramen noodles",
                "1 L chicken stock",
                "2 tbsp soy sauce",
                "1 tbsp miso paste",
                "2 eggs",
                "Spring onion, nori and sesame seeds"
            ),
            steps = listOf(
                "Boil the eggs for 6.5 minutes, then cool them in ice water and peel.",
                "Heat the stock with the soy sauce and miso, without boiling hard.",
                "Cook the noodles according to the packet and drain.",
                "Pour the broth over the noodles and top with halved eggs and garnishes."
            )
        )
    )
}
