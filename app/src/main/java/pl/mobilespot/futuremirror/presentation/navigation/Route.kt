package pl.mobilespot.futuremirror.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object DashboardScreen : Route(route = "onBoardingScreen")

    object SearchScreen : Route(route = "searchScreen")

    object DayDetailsScreen : Route(route = "detailsScreen")

    object NewsScreen : Route(route = "newsNavigator")

    object Settings : Route(route = "settings")
}