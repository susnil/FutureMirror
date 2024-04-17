package pl.mobilespot.futuremirror.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.mobilespot.futuremirror.namedays.GetSavedNameDays
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val getSavedNameDays: GetSavedNameDays) :
    ViewModel() {


    private val _uiState: MutableStateFlow<SettingsState> = MutableStateFlow(SettingsState.raw)
    var uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getSavedNameDays.invoke().also {
                val count = it.count()
                Timber.d("Loaded: $count name days: $it")
                setCount(count)
            }
        }
    }

    fun toggleSwitch() {
        _uiState.value = uiState.value.copy(uiState.value.boolean.not())
    }

    private fun setCount(count: Int) {
        _uiState.update { _uiState.value.copy(nameDayCount = count) }
    }


}

data class SettingsState(val boolean: Boolean, val text: String, val nameDayCount: Int) {
    companion object {
        val raw = SettingsState(false, "", 0)
    }
}