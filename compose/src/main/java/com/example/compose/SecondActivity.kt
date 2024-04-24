package com.example.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.compose.anim.AnimationScreen
import com.example.compose.ui.theme.NewStartTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            NewStartTheme {
                val darkTheme = isSystemInDarkTheme()
                DisposableEffect(darkTheme) {
                    //enableEdgeToEdge()
                    onDispose {}
                }
                // AppScreen()

                AppScreen2()
            }
        }
    }

    @Composable
    fun AppScreen(
        navController: NavHostController = rememberNavController(),
        viewModel: SecondViewModel = hiltViewModel<SecondViewModel>()
    ) {

        val userBean = viewModel._userInfo.collectAsState()

        NavHost(
            navController = navController,
            startDestination = FRIENDS_LIST_ROUTE
        ) {
            addScreen(name = userBean.value.name,
                loadUser = {
                    viewModel.getUserInfo("123")
                },
                onNavigateToFriendsList = {
                    navController.navigate(FRIENDS_LIST_ROUTE)
                },
                onNavigateToProfile = {
                    navController.navigate(PROFILE_ROUTE)
                }
            )
        }
    }

    @Preview
    @Composable
    fun AppScreenPreview() {
        NewStartTheme {
            AppScreen2()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AppScreen2(items: List<Screen> = listOf(Screen.Profile, Screen.FriendsList)) {
        val navController = rememberNavController()
        val scrollBehavior =
            TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text(text = "Title")
                    },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.MoreVert, contentDescription = null)
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Place, contentDescription = null)
                        }
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Home, contentDescription = null)
                        }
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Info, contentDescription = null)
                        }
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Person, contentDescription = null)
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { },
                            containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = null)
                        }
                    }
                )
            }
        ) { innerPadding ->
            val myModifier = Modifier.padding(innerPadding)
            Log.d("SecondActivity", "innerPadding = $innerPadding")
            // MessageListScreen(modifier = Modifier.padding(innerPadding))
            //TextScreen(modifier = myModifier)
//            ButtonScreen(modifier = Modifier.padding(innerPadding))
//            FabScreen(modifier = Modifier.padding(innerPadding))
//            CardScreen(modifier = Modifier.padding(innerPadding))
//            ChipScreen(modifier = Modifier.padding(innerPadding))
//            FlowScreen(modifier = Modifier.padding(innerPadding))

//            HorizontalPagerTest(modifier = Modifier.padding(innerPadding))

            AnimationScreen(Modifier.padding(innerPadding))
        }
    }

    @Composable
    fun AppScreen3() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)

        ) {
            Text(
                color = Color.Black,
                text = "Hello World", modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        color = Color.Yellow
                    )
                    .safeDrawingPadding()
            )
        }
    }
}