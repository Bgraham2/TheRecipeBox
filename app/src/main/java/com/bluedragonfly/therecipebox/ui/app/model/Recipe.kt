package com.bluedragonfly.therecipebox.ui.app.model

import androidx.compose.ui.graphics.Color

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val category: RecipeCategory,
    val prepMinutes: Int,
    val cookMinutes: Int,
    val servings: Int,
    val difficulty: Difficulty,
    val ingredients: List<String>,
    val directions: List<String>,
    val accent: Color,
    val isFavorite: Boolean = false
) {
    val totalMinutes: Int get() = prepMinutes + cookMinutes
}

enum class RecipeCategory(val label: String, val emoji: String) {
    ALL("All", "🍽️"),
    BREAKFAST("Breakfast", "🥞"),
    LUNCH("Lunch", "🥗"),
    DINNER("Dinner", "🍝"),
    DESSERT("Dessert", "🍰"),
    DRINKS("Drinks", "🥤")
}

enum class Difficulty(val label: String) {
    EASY("Easy"),
    MEDIUM("Medium"),
    ADVANCED("Advanced")
}
