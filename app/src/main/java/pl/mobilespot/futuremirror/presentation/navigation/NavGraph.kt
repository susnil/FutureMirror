package pl.mobilespot.futuremirror.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.mobilespot.futuremirror.presentation.dashboard.DashboardRoute
import pl.mobilespot.futuremirror.presentation.days.DayDetailsScreen
import pl.mobilespot.futuremirror.presentation.news.NewsScreen
import pl.mobilespot.futuremirror.presentation.search.SearchScreen
import pl.mobilespot.futuremirror.presentation.settings.SettingsScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.DashboardScreen.route) {
        composable(route = Route.DashboardScreen.route) { backStackEntry ->
            DashboardRoute()
        }
        composable(route = Route.SearchScreen.route) { backStackEntry ->
            SearchScreen()
        }
        composable(route = Route.DayDetailsScreen.route) { backStackEntry ->
            DayDetailsScreen()
        }
        composable(route = Route.NewsScreen.route) { backStackEntry ->
            NewsScreen()
        }
        composable(route = Route.Settings.route) { backStackEntry ->
            SettingsScreen()
        }
    }
}