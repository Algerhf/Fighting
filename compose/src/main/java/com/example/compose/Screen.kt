package com.example.compose

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes title: Int) {
    data object Profile : Screen(PROFILE_ROUTE, R.string.title_profile)
    data object FriendsList : Screen(FRIENDS_LIST_ROUTE, R.string.title_friends)
}