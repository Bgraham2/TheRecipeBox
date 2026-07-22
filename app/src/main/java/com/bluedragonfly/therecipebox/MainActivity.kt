package com.bluedragonfly.therecipebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bluedragonfly.therecipebox.ui.app.TheRecipeBoxApp
import com.bluedragonfly.therecipebox.ui.app.theme.TheRecipeBoxTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheRecipeBoxTheme {
                TheRecipeBoxApp()
            }
        }
    }
}
