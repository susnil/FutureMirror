package pl.mobilespot.futuremirror.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import pl.mobilespot.futuremirror.presentation.navigation.Route.DayDetailsScreen.DAY_ID_ARG

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object DashboardScreen : Route(route = "onBoardingScreen")

    object SearchScreen : Route(route = "searchScreen")

    object DayDetailsScreen : Route(route = "detailsScreen") {
        const val DAY_ID_ARG = "dayId"
    }

    object NewsScreen : Route(route = "newsNavigator")

    object Settings : Route(route = "settings")
}

fun NavController.navigateToDayDetails(dayId: String? = null, navOptions: NavOptions? = null) {
    val route = if (dayId != null) {
        "${Route.DayDetailsScreen.route}?${DAY_ID_ARG}=$dayId"
    } else {
        Route.DayDetailsScreen.route
    }
    navigate(route, navOptions)
}