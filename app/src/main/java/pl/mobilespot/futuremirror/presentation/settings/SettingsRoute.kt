package pl.mobilespot.futuremirror.presentation.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsRoute(viewModel: SettingsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val switch by viewModel.uiState.collectAsState()
    SettingsScreen(uiState) { viewModel.toggleSwitch() }
}