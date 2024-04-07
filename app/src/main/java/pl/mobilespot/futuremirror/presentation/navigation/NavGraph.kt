package pl.mobilespot.futuremirror.presentation.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.mobilespot.futuremirror.R
import pl.mobilespot.futuremirror.presentation.dashboard.DashboardRoute
import pl.mobilespot.futuremirror.presentation.days.DayDetailsScreen
import pl.mobilespot.futuremirror.presentation.news.NewsScreen
import pl.mobilespot.futuremirror.presentation.search.SearchScreen
import pl.mobilespot.futuremirror.presentation.settings.SettingsRoute

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
            SettingsRoute()
        }
    }
    Button(onClick = { navController.navigate(Route.Settings.route) }) {
        Text(text = stringResource(id = R.string.settings))
    }
}