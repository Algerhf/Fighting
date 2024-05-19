package com.example.compose.anim

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

/**
 * 指定EnterTransition和ExitTransition来自定义过渡效果。
 */
@Composable
fun TestAnimatedVisibility() {
    val reusedModifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .clip(RoundedCornerShape(10.dp))

    var visible by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            with(density) { -500.dp.roundToPx() }
        } + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Box(modifier = reusedModifier.background(Color.Green))
    }

    Box(
        modifier = reusedModifier
            .background(Color.Blue)
            .clickable {
                visible = !visible
            }
    )
}

/**
 * MutableTransitionState参数
 */
@Composable
fun TestMutableTransitionStateForAnimatedVisibility() {
    val state = remember {
        MutableTransitionState(false).apply { targetState = true }
    }
    AnimatedVisibility(visibleState = state) {
        Text(text = "Hello World")
    }
    Text(
        text = when {
            state.isIdle && state.currentState -> "visible"
            !state.isIdle && state.currentState -> "Disappearing"
            state.isIdle && !state.currentState -> "invisible"
            else -> "Appearing"
        },
        modifier = Modifier.clickable { state.targetState = !state.currentState }
    )
}

/**
 * 为子项添加进入和退出动画效果
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TestAnimateEnterExitForAnimatedVisibility() {
    var visible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(), //
        exit = fadeOut()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .animateEnterExit(enter = slideInVertically(), exit = slideOutVertically())
                    .sizeIn(minWidth = 256.dp, minHeight = 64.dp)
                    .background(Color.Red)
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Blue)
            .clickable {
                visible = !visible
            }
    )
}

/**
 * 添加自定义动画
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TestTransitionForAnimatedVisibility() {
    var visible by remember { mutableStateOf(true) }
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        val background by transition.animateColor(label = "color") {
            if (it == EnterExitState.Visible) Color.Blue else Color.DarkGray
        }
        Box(
            modifier = Modifier
                .size(128.dp)
                .background(background)
                .clickable {
                    visible = !visible
                }
        )
    }
}