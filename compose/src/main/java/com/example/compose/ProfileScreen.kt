package com.example.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier


const val PROFILE_ROUTE = "profile"

@Composable
fun ProfileScreen(
    name: String,
    loadUser: () -> Unit,
    onNavigateToFriendsList: () -> Unit,
    modifier: Modifier = Modifier
) {

    val load by rememberUpdatedState(newValue = loadUser)

    LaunchedEffect(Unit) {
        load()
    }

    Column(modifier = modifier) {
        Text(text = "profile $name")
        Button(onClick = onNavigateToFriendsList) {
            Text(text = "jump to FriendList")
        }
    }
}
