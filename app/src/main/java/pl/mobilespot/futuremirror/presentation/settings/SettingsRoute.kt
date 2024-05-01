package pl.mobilespot.futuremirror.presentation.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SettingsRoute(viewModel: SettingsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val settings by viewModel.settings.collectAsState()
    val namesCount by viewModel.getAllNames().collectAsStateWithLifecycle(initialValue = 0)
    SettingsScreen(uiState, settings, namesCount) { viewModel.toggleSwitch() }
}