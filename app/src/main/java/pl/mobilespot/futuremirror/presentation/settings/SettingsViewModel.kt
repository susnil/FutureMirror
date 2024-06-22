package pl.mobilespot.futuremirror.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pl.mobilespot.futuremirror.datastore.UserPreferences
import pl.mobilespot.futuremirror.datastore.UserPreferencesRepository
import pl.mobilespot.futuremirror.namedays.usecase.GetSavedNameDays
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSavedNameDays: GetSavedNameDays,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val settings: StateFlow<UserPreferences?> = userPreferencesRepository.userPreferences
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            null,
        )

    private val _uiState = MutableStateFlow(SettingsState.raw)
    val uiState: StateFlow<SettingsState> get() = _uiState

    fun getAllNames(): Flow<Int> =
        getSavedNameDays.getDayNamesCount()

    fun toggleSwitch() {
        val next = !(settings.value ?: UserPreferences(false)).showCompleted
        viewModelScope.launch { userPreferencesRepository.updateShowCompleted(next) }
    }

}

data class SettingsState(val boolean: Boolean, val text: String) {
    companion object {
        val raw = SettingsState(false, "")
    }
}
