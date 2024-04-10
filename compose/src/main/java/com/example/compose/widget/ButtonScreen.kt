package com.example.compose.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ButtonScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Filled Button")
        }

        FilledTonalButton(onClick = { /*TODO*/ }) {
            Text(text = "Filled Tonal Button")
        }

        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(text = "OutlinedButton")
        }

        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(text = "ElevatedButton")
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "TextButton")
        }
    }
}