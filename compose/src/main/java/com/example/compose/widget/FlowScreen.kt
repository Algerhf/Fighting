package com.example.compose.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowScreen(modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier.padding(horizontal = 10.dp),
        maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ChipItem("View")
        ChipItem("Jetpack Compose")
        ChipItem("Java")
        ChipItem("Kotlin Android")
        ChipItem("C++")
        ChipItem("Kotlin")
        ChipItem("English")
        ChipItem("Study Compose")
        ChipItem("Chinese Chinese")
        ChipItem("C++")
        ChipItem("Swift")
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowScreen2(modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier.padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = 3
    ) {
        val itemModifier = Modifier.clip(RoundedCornerShape(8.dp))
        Box(
            modifier = itemModifier
                .height(200.dp)
                .width(60.dp)
                .background(Color.Red)
        ) {}
        Box(
            modifier = itemModifier
                .height(200.dp)
                .fillMaxWidth(0.7f)
                .background(Color.Blue)
        ) {}
        Box(
            modifier = itemModifier
                .height(200.dp)
                .weight(1f)
                .background(Color.Magenta)
        )
    }
}

@Preview
@Composable
fun FlowScreenPreview() {
    FlowScreen()
}

@Preview
@Composable
fun FlowScreenPreview2() {
    FlowScreen2()
}

@Composable
fun ChipItem(text: String) {
    var selected by rememberSaveable {
        mutableStateOf(true)
    }
    ElevatedFilterChip(
        selected = selected,
        onClick = { selected = !selected },
        label = { Text(text = text) },
        leadingIcon = if (selected) {
            { Icon(Icons.Filled.Check, contentDescription = null) }
        } else {
            null
        })
}