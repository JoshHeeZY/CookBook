# 🍳 CookBook

A simple recipe app for Android, built with **Kotlin** and **Jetpack Compose**.
Browse your recipes, search them, and add your own with a photo, ingredients and step-by-step instructions.

![Kotlin](https://img.shields.io/badge/Kotlin-1.9-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?logo=jetpackcompose&logoColor=white)
![Min SDK](https://img.shields.io/badge/minSdk-24-3DDC84?logo=android&logoColor=white)
![License](https://img.shields.io/badge/license-MIT-blue)

---

## ✨ Features

- **Recipe feed** with photo cards, cooking time and a colour-coded difficulty badge
- **Search** by recipe name, description or ingredient
- **Filter** by difficulty (Easy / Medium / Hard)
- **Recipe details** with ingredients list and numbered steps
- **Add recipes** with validation, a difficulty picker, ingredients and steps
- **Photos** from the system photo picker or the camera (full resolution, no storage permission needed)
- **Delete** recipes with a confirmation dialog
- Warm custom **Material 3** theme with light and dark mode, edge-to-edge layout

## 📸 Screenshots

| Home | Detail | Add recipe |
|------|--------|------------|
| _coming soon_ | _coming soon_ | _coming soon_ |

<!-- Put screenshots in docs/screenshots/ and link them here, e.g. ![Home](docs/screenshots/home.png) -->

## 🧩 Tech stack

| | |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Architecture | MVVM (ViewModel + Compose state) |
| Navigation | Navigation Compose |
| Images | Coil |
| Photo / camera | AndroidX Activity Result API (`PickVisualMedia`, `TakePicture`) + `FileProvider` |

## 🗂️ Project structure

```
app/src/main/java/com/joshuahee/cookbook/
├── MainActivity.kt          # Entry point + navigation graph
├── data/
│   ├── Models.kt            # Recipe, Difficulty, formatting helpers
│   └── SampleRecipes.kt     # Built-in example recipes
└── ui/
    ├── RecipeViewModel.kt   # Recipe list, search & filter state
    ├── components/          # Shared composables (image, badges)
    ├── screens/             # Home, Detail, AddRecipe
    └── theme/               # Colours, typography, theme
```

## 📱 How to run

1. Clone the repo:
   ```bash
   git clone https://github.com/JoshHeeZY/CookBook.git
   ```
2. Open the folder in **Android Studio** (Hedgehog or newer).
3. Let Gradle sync, then run the `app` configuration on an emulator or device (Android 7.0+).

Run the unit tests with:

```bash
./gradlew test
```

## 🌿 Branch workflow

Each feature is built on its own branch and merged into `main` with a pull request:

```
feature/<short-name>     e.g. feature/add-recipe, feature/ui-grid
```

```bash
git checkout main && git pull
git checkout -b feature/my-new-feature
# ...commit work...
git push -u origin feature/my-new-feature
```

## 🛣️ Roadmap

- [ ] Save recipes locally with Room so they survive app restarts
- [ ] Edit existing recipes
- [ ] Favourites
- [ ] Recipe categories (Breakfast, Dessert, ...)

## 📄 License

Released under the [MIT License](LICENSE).
