package pl.mobilespot.futuremirror.presentation.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pl.mobilespot.futuremirror.R
import pl.mobilespot.futuremirror.presentation.dashboard.DashboardRoute
import pl.mobilespot.futuremirror.presentation.days.DayDetailsScreen
import pl.mobilespot.futuremirror.presentation.news.NewsScreen
import pl.mobilespot.futuremirror.presentation.search.SearchRoute
import pl.mobilespot.futuremirror.presentation.settings.SettingsRoute

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.DashboardScreen.route) {
        composable(route = Route.DashboardScreen.route) { _ ->
            DashboardRoute()
        }
        composable(route = Route.SearchScreen.route) { _ ->
            SearchRoute()
        }
        composable(
            route = Route.DayDetailsScreen.route,
            arguments = listOf(navArgument(Route.DayDetailsScreen.DAY_ID_ARG) {
                type = NavType.StringType
                defaultValue = null
                nullable = true
            })
        ) { _ ->
            //todo handle arg
            DayDetailsScreen()
        }
        composable(route = Route.NewsScreen.route) { _ ->
            NewsScreen()
        }
        composable(route = Route.Settings.route) { _ ->
            SettingsRoute()
        }
    }
    Row {
        listOf(
            Route.Settings.route to R.string.settings,
            Route.SearchScreen.route to R.string.search,
            Route.DayDetailsScreen.route to R.string.day_details
        ).forEach {
            Button(onClick = { navController.navigate(it.first) }) {
                Text(text = stringResource(id = it.second))
            }
        }
        //todo remove
        Button(onClick = { navController.navigateToDayDetails("4") }) {
            Text(text = "Go to 4 day details")
        }
    }

}