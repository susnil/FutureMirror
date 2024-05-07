package pl.mobilespot.futuremirror.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val bottomNavigationItems =
        listOf(
            Route.DashboardScreen.route to BottomNavigationItem(
                R.drawable.ic_dashboard,
                R.string.dashboard
            ),
            Route.SearchScreen.route to BottomNavigationItem(R.drawable.ic_search, R.string.search),
            Route.DayDetailsScreen.route to BottomNavigationItem(
                R.drawable.ic_day_details,
                R.string.day_details
            ),
            Route.Settings.route to BottomNavigationItem(R.drawable.ic_settings, R.string.settings),
        )
    //todo remove
//    Button(onClick = { navController.navigateToDayDetails("4") }) {
//        Text(text = "Go to 4 day details")
//    }
    Scaffold(bottomBar = {
        BottomNavigation(
            items = bottomNavigationItems.map {
                it.second
            },
            selectedItem = 0,//todo selectedItem
            onItemClick = { index ->
                navController.navigate(bottomNavigationItems[index].first)
            }
        )
    }) {
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
    }
}
