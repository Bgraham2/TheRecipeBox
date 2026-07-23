package com.bluedragonfly.therecipebox.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

enum class TheRecipeBoxWindowSize {
    Compact,
    Medium,
    Expanded
}

@Composable
fun rememberRecipeWindowSize(): TheRecipeBoxWindowSize {
    val width = LocalWindowInfo.current.containerDpSize.width

    return when {
        width < 600.dp -> TheRecipeBoxWindowSize .Compact
        width < 840.dp -> TheRecipeBoxWindowSize .Medium
        else -> TheRecipeBoxWindowSize .Expanded
    }
}