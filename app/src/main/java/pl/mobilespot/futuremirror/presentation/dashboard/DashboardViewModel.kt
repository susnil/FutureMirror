package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import pl.mobilespot.futuremirror.datastore.UserPreferences
import pl.mobilespot.futuremirror.datastore.UserPreferencesRepository
import pl.mobilespot.futuremirror.namedays.DayMonth
import pl.mobilespot.futuremirror.namedays.NameDaysRepository
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val nameDaysRepository: NameDaysRepository,
    private val savedStateHandle: SavedStateHandle,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    companion object {
        const val UI_STATE = "dashboard_ui_state"
    }

    val uiState = savedStateHandle.getStateFlow(UI_STATE, DashboardState.raw)

    val settings: StateFlow<UserPreferences?> = userPreferencesRepository.userPreferences
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            null,
        ).also { Timber.d("user settings: ${it.value}") }

    init {
        val stateUi: DashboardState? = savedStateHandle[UI_STATE]

        Timber.d("Init ViewModel, StateUi: $stateUi")
        updateNameDay()
    }

    private fun updateNameDay() {
        val localDate = LocalDate.now()
        val dayMonth =
            DayMonth(uiState.value.selectedDay ?: localDate.dayOfMonth, localDate.monthValue)
        val nameDays = nameDaysRepository.getNamesForDay(dayMonth)
        savedStateHandle[UI_STATE] = uiState.value.copy(namesDay = nameDays)
        Timber.d("Name days $nameDays")
    }

    fun selectDay(day: Int) {
        savedStateHandle[UI_STATE] = uiState.value.copy(selectedDay = day)
        Timber.d("select day: $day")
        updateNameDay()
    }

    fun unselect() {
        savedStateHandle[UI_STATE] = uiState.value.copy(selectedDay = null)
        Timber.d("Unselect day")
        updateNameDay()
    }
}

