package com.example.compose.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(onClick = { }) {
            Text(text = "Filled Button")
        }

        FilledTonalButton(onClick = {}) {
            Text(text = "Filled Tonal Button")
        }

        OutlinedButton(onClick = {}) {
            Text(text = "OutlinedButton")
        }

        ElevatedButton(onClick = {}) {
            Text(text = "ElevatedButton")
        }

        TextButton(onClick = {}) {
            Text(text = "TextButton")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonScreenPreview() {
    ButtonScreen()
}