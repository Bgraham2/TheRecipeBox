package com.bluedragonfly.therecipebox.ui.app.data

import androidx.compose.ui.graphics.Color
import com.bluedragonfly.therecipebox.ui.app.model.Difficulty
import com.bluedragonfly.therecipebox.ui.app.model.Recipe
import com.bluedragonfly.therecipebox.ui.app.model.RecipeCategory

val sampleRecipes = listOf(
    Recipe(
        id = 1,
        title = "Blueberry Oat Pancakes",
        description = "Fluffy whole-grain pancakes with blueberries and a hint of vanilla.",
        category = RecipeCategory.BREAKFAST,
        prepMinutes = 10,
        cookMinutes = 15,
        servings = 4,
        difficulty = Difficulty.EASY,
        ingredients = listOf(
            "1½ cups rolled oats", "1 cup milk", "2 eggs", "1 tsp vanilla",
            "1 tsp baking powder", "1 cup blueberries", "Maple syrup, to serve"
        ),
        directions = listOf(
            "Blend oats into a fine flour.",
            "Whisk milk, eggs, and vanilla, then fold in the oat flour and baking powder.",
            "Gently fold in blueberries.",
            "Cook ¼-cup portions on a lightly greased skillet until golden on both sides."
        ),
        accent = Color(0xFF0D47A1),
        isFavorite = true
    ),
    Recipe(
        id = 2,
        title = "Citrus Quinoa Bowl",
        description = "Bright quinoa, avocado, greens, and citrus vinaigrette.",
        category = RecipeCategory.LUNCH,
        prepMinutes = 15,
        cookMinutes = 20,
        servings = 2,
        difficulty = Difficulty.EASY,
        ingredients = listOf(
            "1 cup cooked quinoa", "2 cups mixed greens", "1 avocado",
            "1 orange", "¼ cup pumpkin seeds", "2 tbsp olive oil", "1 tbsp lemon juice"
        ),
        directions = listOf(
            "Divide quinoa and greens between bowls.",
            "Top with sliced avocado, orange segments, and pumpkin seeds.",
            "Whisk olive oil and lemon juice, season, and drizzle over each bowl."
        ),
        accent = Color(0xFF00ACC1)
    ),
    Recipe(
        id = 3,
        title = "Creamy Tuscan Pasta",
        description = "A comforting one-pan pasta with spinach, tomato, and Parmesan.",
        category = RecipeCategory.DINNER,
        prepMinutes = 12,
        cookMinutes = 25,
        servings = 4,
        difficulty = Difficulty.MEDIUM,
        ingredients = listOf(
            "12 oz penne", "2 cloves garlic", "1 cup cherry tomatoes",
            "2 cups spinach", "1 cup cream", "½ cup Parmesan", "Italian herbs"
        ),
        directions = listOf(
            "Cook pasta until al dente and reserve ½ cup pasta water.",
            "Sauté garlic and tomatoes until softened.",
            "Add cream, Parmesan, herbs, and spinach.",
            "Toss in pasta, loosening the sauce with reserved water as needed."
        ),
        accent = Color(0xFF5E35B1),
        isFavorite = true
    ),
    Recipe(
        id = 4,
        title = "Chocolate Berry Parfait",
        description = "Layered chocolate yogurt, berries, and crunchy granola.",
        category = RecipeCategory.DESSERT,
        prepMinutes = 10,
        cookMinutes = 0,
        servings = 2,
        difficulty = Difficulty.EASY,
        ingredients = listOf(
            "1½ cups Greek yogurt", "2 tbsp cocoa powder", "1 tbsp honey",
            "1 cup mixed berries", "½ cup granola", "Dark chocolate shavings"
        ),
        directions = listOf(
            "Mix yogurt, cocoa, and honey until smooth.",
            "Layer chocolate yogurt, berries, and granola in two glasses.",
            "Finish with chocolate shavings and serve."
        ),
        accent = Color(0xFFFB8C00)
    ),
    Recipe(
        id = 5,
        title = "Mint Lime Cooler",
        description = "A crisp, alcohol-free cooler with fresh lime and mint.",
        category = RecipeCategory.DRINKS,
        prepMinutes = 8,
        cookMinutes = 0,
        servings = 2,
        difficulty = Difficulty.EASY,
        ingredients = listOf(
            "2 limes", "12 mint leaves", "2 tsp honey", "2 cups sparkling water", "Ice"
        ),
        directions = listOf(
            "Muddle lime, mint, and honey in a pitcher.",
            "Add ice and sparkling water.",
            "Stir gently and pour into chilled glasses."
        ),
        accent = Color(0xFF2E7D32)
    )
)
