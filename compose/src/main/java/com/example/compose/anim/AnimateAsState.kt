package com.example.compose.anim

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun TestAnimateAsState() {
    var enable by remember {
        mutableStateOf(true)
    }
    val alpha by animateFloatAsState(
        targetValue = if (enable) 1.0f else 0.5f,
        label = "alpha"
    )
    Box(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .graphicsLayer(alpha = alpha)
            .background(Color.Red)
    ) {
    }
    Button(onClick = { enable = !enable }) {
        Text(text = "禁用/启用")
    }
}