package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DashboardRoute(viewModel: DashboardViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val userPreferences by viewModel.settings.collectAsStateWithLifecycle()
    DashboardScreen(uiState, userPreferences, { day -> viewModel.selectDay(day) }) { viewModel.unselect() }
}
