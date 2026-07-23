package com.bluedragonfly.therecipebox.ui.app

import androidx.lifecycle.ViewModel
import com.bluedragonfly.therecipebox.ui.app.data.sampleRecipes
import com.bluedragonfly.therecipebox.ui.app.model.Recipe
import com.bluedragonfly.therecipebox.ui.app.model.RecipeCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class RecipeBoxUiState(
    val recipes: List<Recipe> = emptyList(),
    val query: String = "",
    val selectedCategory: RecipeCategory = RecipeCategory.ALL
) {
    val filteredRecipes: List<Recipe>
        get() = recipes.filter { recipe ->
            val matchesCategory =
                selectedCategory == RecipeCategory.ALL || recipe.category == selectedCategory
            val matchesQuery =
                query.isBlank() ||
                    recipe.title.contains(query, ignoreCase = true) ||
                    recipe.description.contains(query, ignoreCase = true) ||
                    recipe.ingredients.any { it.contains(query, ignoreCase = true) }
            matchesCategory && matchesQuery
        }

    val favoriteRecipes: List<Recipe>
        get() = recipes.filter(Recipe::isFavorite)
}

class RecipeBoxViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RecipeBoxUiState(recipes = sampleRecipes))
    val uiState: StateFlow<RecipeBoxUiState> = _uiState.asStateFlow()

    fun updateQuery(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun selectCategory(category: RecipeCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun toggleFavorite(recipeId: Int) {
        _uiState.update { state ->
            state.copy(
                recipes = state.recipes.map { recipe ->
                    if (recipe.id == recipeId) recipe.copy(isFavorite = !recipe.isFavorite)
                    else recipe
                }
            )
        }
    }
}
