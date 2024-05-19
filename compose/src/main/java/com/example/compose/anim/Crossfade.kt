package com.example.compose.anim

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun TestCrossfade() {
    var currentPage by remember { mutableStateOf("A") }
    Crossfade(targetState = currentPage) { screen ->
        when (screen) {
            "A" -> Text(text = "Page A", modifier = Modifier.clickable { currentPage = "B" })
            "B" -> Text(text = "Page B", modifier = Modifier.clickable { currentPage = "A" })
        }
    }
}