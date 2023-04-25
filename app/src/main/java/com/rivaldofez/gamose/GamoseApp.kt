package com.rivaldofez.gamose

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rivaldofez.gamose.ui.navigation.NavigationItem
import com.rivaldofez.gamose.ui.navigation.Screen
import com.rivaldofez.gamose.ui.screen.DetailScreen
import com.rivaldofez.gamose.ui.screen.FavoriteScreen
import com.rivaldofez.gamose.ui.screen.HomeScreen
import com.rivaldofez.gamose.ui.screen.ProfileScreen
import com.rivaldofez.gamose.ui.theme.GamoseTheme
import dagger.hilt.android.HiltAndroidApp


@Composable
fun GamoseApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailGame.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { gameId ->
                    navController.navigate(Screen.DetailGame.createRoute(gameId))
                })
            }

            composable(Screen.Favorite.route) {
                FavoriteScreen(navigateToDetail = {})
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(
                route = Screen.DetailGame.route,
                arguments = listOf(navArgument("gameId") { type = NavType.IntType}),
            ) {
                val id = it.arguments?.getInt("gameId") ?: 0
                DetailScreen(
                    gameId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Favorite",
                icon = Icons.Default.Search,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = "Profile",
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile
            )
        )

        BottomNavigation {
            navigationItems.map { navItem ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = navItem.icon,
                            contentDescription = navItem.title
                        )
                    },
                    label = { Text(navItem.title) },
                    selected = currentRoute == navItem.screen.route,
                    onClick = {
                        navController.navigate(navItem.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamoseAppPreview(){
    GamoseTheme {
        GamoseApp()
    }
}