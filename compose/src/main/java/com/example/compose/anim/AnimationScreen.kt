package com.example.compose.anim

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimationScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Bottom)
    ) {
        //TestAnimatedVisibility()
        //TestMutableTransitionStateForAnimatedVisibility()
        //TestTransitionForAnimatedVisibility()
        //TestAnimatedContent()
        //TestTransitionSpecForAnimatedContent()
        //TestSizeTransformForAnimatedContent()
        //TestCrossfade()
        // TestAnimateContentSize()
//        TestAnimateAsState()
//        TestUpdateTransition()
//        TestTransitionSpec()
        TestMutableTransitionState()
    }
}

@Preview(showBackground = true)
@Composable
fun AnimationScreenPreview() {
    AnimationScreen()
}




