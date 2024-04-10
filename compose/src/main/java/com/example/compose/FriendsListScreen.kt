package com.example.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

const val FRIENDS_LIST_ROUTE = "friendsList"

@Composable
fun FriendsListScreen(modifier: Modifier = Modifier, onNavigateToProfile: () -> Unit) {
    Column(modifier = modifier) {
        Text(text = "friendsList")
        Button(onClick = onNavigateToProfile) {
            Text(text = "jump to profile")
        }
    }
}