package com.example.compose

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addScreen(
    name: String,
    loadUser: () -> Unit,
    onNavigateToFriendsList: () -> Unit,
    onNavigateToProfile: () -> Unit,
) {
    composable(PROFILE_ROUTE) {
        ProfileScreen(
            name,
            loadUser = loadUser,
            onNavigateToFriendsList = onNavigateToFriendsList
        )
    }

    composable(FRIENDS_LIST_ROUTE) {
        FriendsListScreen(onNavigateToProfile = onNavigateToProfile)
    }
}