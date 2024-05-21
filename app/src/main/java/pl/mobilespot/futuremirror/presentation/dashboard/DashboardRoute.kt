package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DashboardRoute(viewModel: DashboardViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val dayOfMoths by viewModel.dayOfMoths.collectAsStateWithLifecycle()
    DashboardScreen(
        uiState,
        { day -> viewModel.selectDay(day) },
        { viewModel.unselect() },
        dayOfMoths
    )
}
