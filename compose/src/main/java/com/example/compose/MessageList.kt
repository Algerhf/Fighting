package com.example.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun MessageListScreen(
    modifier: Modifier = Modifier,
    list: List<String> = List(100) { index -> "$index" }
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(list) {
            MessageItem(msg = it)
        }
    }
}

@Preview
@Composable
fun MessageListScreenPreview() {
    MessageListScreen()
}

@Composable
fun MessageItem(msg: String) {
    Text(text = msg, modifier = Modifier.fillMaxWidth())
}