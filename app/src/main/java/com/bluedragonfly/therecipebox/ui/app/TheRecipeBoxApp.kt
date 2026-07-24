package com.bluedragonfly.therecipebox.ui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bluedragonfly.therecipebox.ui.app.screens.AddRecipeScreen
import com.bluedragonfly.therecipebox.ui.app.screens.FavoritesScreen
import com.bluedragonfly.therecipebox.ui.app.screens.HomeScreen
import com.bluedragonfly.therecipebox.ui.app.screens.RecipeDetailScreen

private sealed class Destination(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    data object Home : Destination("home", "Home", Icons.Default.Home)
    data object Favorites : Destination("favorites", "Favorites", Icons.Default.Favorite)
    data object Add : Destination("add", "Add", Icons.Default.Add)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TheRecipeBoxApp(viewModel: RecipeBoxViewModel = viewModel()) {
    val navController = rememberNavController()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val destination = backStackEntry?.destination
    val topLevel = listOf(Destination.Home, Destination.Favorites)
    val windowSize = rememberRecipeWindowSize()
    val isExpanded = windowSize == TheRecipeBoxWindowSize.Expanded

    Scaffold(
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            if (!isExpanded && destination?.route?.startsWith("recipe/") != true &&
                destination?.route != Destination.Add.route
            ) {
                NavigationBar {
                    topLevel.forEach { item ->
                        NavigationBarItem(
                            selected = destination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            if (destination?.route == Destination.Home.route) {
                FloatingActionButton(
                    onClick = { navController.navigate(Destination.Add.route) },
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add recipe")
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
        ) {
            NavHost(
                navController = navController,
                startDestination = Destination.Home.route
            ) {
                composable(Destination.Home.route) {
                    HomeScreen(
                        uiState = uiState,
                        isExpanded = isExpanded,
                        onQueryChange = viewModel::updateQuery,
                        onCategorySelected = viewModel::selectCategory,
                        onRecipeClick = { navController.navigate("recipe/${it.id}") },
                        onFavoriteClick = { viewModel.toggleFavorite(it.id) },
                        onFavoritesClick = { navController.navigate(Destination.Favorites.route) }
                    )
                }
                composable(Destination.Favorites.route) {
                    FavoritesScreen(
                        recipes = uiState.favoriteRecipes,
                        isExpanded = isExpanded,
                        onRecipeClick = { navController.navigate("recipe/${it.id}") },
                        onFavoriteClick = { viewModel.toggleFavorite(it.id) }
                    )
                }
                composable(Destination.Add.route) {
                    AddRecipeScreen(onBack = navController::popBackStack)
                }
                composable("recipe/{recipeId}") { entry ->
                    val id = entry.arguments?.getString("recipeId")?.toIntOrNull()
                    val recipe = uiState.recipes.firstOrNull { it.id == id }
                    RecipeDetailScreen(
                        recipe = recipe,
                        onBack = navController::popBackStack,
                        onFavoriteClick = { recipe?.let { viewModel.toggleFavorite(it.id) } }
                    )
                }
            }
        }
    }
}