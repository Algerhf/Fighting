package com.example.compose.anim

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun TestAnimatedContent() {
    var count by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(onClick = { count++ }) {
            Text(text = "Add")
        }
        AnimatedContent(targetState = count) { targetCount ->
            Text(text = "count $targetCount")
        }
    }
}

@Composable
fun TestTransitionSpecForAnimatedContent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        var count by remember { mutableStateOf(100) }
        Button(onClick = {
            if (count > 0) {
                count--
            }
        }) {
            Text(text = "-")
        }
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically { fullHeight -> fullHeight } + fadeIn() togetherWith
                            slideOutVertically { fullHeight -> -fullHeight } + fadeOut()
                } else {
                    slideInVertically { fullHeight -> -fullHeight } + fadeIn() togetherWith
                            slideOutVertically { fullHeight -> fullHeight } + fadeOut()
                }.using(SizeTransform(clip = false))
            }, label = "count"
        ) {
            Text(text = "$it")
        }
        Button(onClick = { count++ }) {
            Text(text = "+")
        }
    }
}

@Composable
fun TestSizeTransformForAnimatedContent() {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        color = MaterialTheme.colorScheme.primary,
        onClick = { expanded = !expanded }
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) togetherWith
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }, label = "expanded"
        ) { targetExpanded ->
            if (targetExpanded) {
                Text(text = "展开后的内容".repeat(30))
            } else {
                Icon(imageVector = Icons.Filled.Phone, contentDescription = null)
            }
        }
    }
}