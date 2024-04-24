package com.example.compose.anim

import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun TestUpdateTransition() {
    var currentState by remember {
        mutableStateOf(BoxState.Collapsed)
    }
    val transition = updateTransition(targetState = currentState, label = "box state")
    val borderWidth by transition.animateDp(label = "border width") { state ->
        when (state) {
            BoxState.Collapsed -> 1.dp
            BoxState.Expanded -> 2.dp
        }
    }
    val color by transition.animateColor(label = "color") { state ->
        when (state) {
            BoxState.Collapsed -> Color.Blue
            BoxState.Expanded -> Color.Red
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .graphicsLayer { }
        .border(
            width = borderWidth,
            shape = RoundedCornerShape(5.dp),
            color = color
        )
        .clickable {
            currentState =
                if (currentState == BoxState.Expanded) {
                    BoxState.Collapsed
                } else {
                    BoxState.Expanded
                }
        })
}

@Composable
fun TestTransitionSpec() {
    var currentState by remember {
        mutableStateOf(BoxState.Collapsed)
    }
    val transition = updateTransition(targetState = currentState, label = "box state")
    val borderWidth by transition.animateDp(label = "border width") { state ->
        when (state) {
            BoxState.Collapsed -> 1.dp
            BoxState.Expanded -> 2.dp
        }
    }
    val color by transition.animateColor(label = "color",
        transitionSpec = {
            when {
                BoxState.Expanded isTransitioningTo BoxState.Collapsed ->
                    spring(stiffness = 50f)

                else ->
                    tween(durationMillis = 500)
            }
        }) { state ->
        when (state) {
            BoxState.Collapsed -> Color.Blue
            BoxState.Expanded -> Color.Red
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .drawBehind {
            drawRect(color = color)
        }
        .border(
            border = BorderStroke(width = borderWidth, color = Color.Black),
            shape = RoundedCornerShape(5.dp),
        )
        .clickable {
            currentState =
                if (currentState == BoxState.Expanded) {
                    BoxState.Collapsed
                } else {
                    BoxState.Expanded
                }
        })
}

@Composable
fun TestMutableTransitionState() {
    val boxState = remember {
        MutableTransitionState(initialState = BoxState.Collapsed)
    }
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        boxState.targetState = BoxState.Expanded
    }

    val transition = updateTransition(targetState = boxState, label = "box state")

    val borderWidth by transition.animateDp(label = "border width") { state ->
        when (state.targetState) {
            BoxState.Collapsed -> 1.dp
            BoxState.Expanded -> 20.dp
        }
    }
    val color by transition.animateColor(label = "color") { state ->
        when (state.targetState) {
            BoxState.Collapsed -> Color.Blue
            BoxState.Expanded -> Color.Red
        }
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .drawBehind {
            drawRect(color = color)
        }
        .border(
            border = BorderStroke(width = borderWidth, color = Color.Black),
            shape = RoundedCornerShape(5.dp),
        )
        .clickable {
            Log.d("点击时","过渡完成 = ${transition.currentState == transition.targetState}")
            if (boxState.targetState == BoxState.Expanded) {
                boxState.targetState = BoxState.Collapsed
            } else {
                boxState.targetState = BoxState.Expanded
            }
        })
}


enum class BoxState {
    Collapsed,
    Expanded
}

class TransitionData(
    color: State<Color>,
    size: State<Dp>
) {
    val color by color
    val size by size
}

@Composable
fun updateTransitionData(boxState: BoxState): TransitionData {
    val transition = updateTransition(targetState = boxState, label = "box state")
    val color = transition.animateColor(label = "color") {
        when (it) {
            BoxState.Expanded -> MaterialTheme.colorScheme.primary
            BoxState.Collapsed -> MaterialTheme.colorScheme.secondary
        }
    }
    val size = transition.animateDp(label = "size") {
        when (it) {
            BoxState.Expanded -> 68.dp
            BoxState.Collapsed -> 142.dp
        }
    }
    return remember(transition) {
        TransitionData(color = color, size = size)
    }
}

