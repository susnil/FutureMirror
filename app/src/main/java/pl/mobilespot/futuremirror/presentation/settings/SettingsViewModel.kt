package pl.mobilespot.futuremirror.presentation.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.mobilespot.futuremirror.namedays.GetSavedNameDays
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val getSavedNameDays: GetSavedNameDays) :
    ViewModel() {

    private val _uiState: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState.raw)
    var uiState = _uiState.asStateFlow()
    fun getAllNames(): Flow<Int> =
        getSavedNameDays.getDayNamesCount()

    fun toggleSwitch() {
        _uiState.value = uiState.value.copy(uiState.value.boolean.not())
    }

}

data class SettingsState(val boolean: Boolean, val text: String) {
    companion object {
        val raw = SettingsState(false, "")
    }
}