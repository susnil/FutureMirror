package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.mobilespot.futuremirror.namedays.DayMonth
import pl.mobilespot.futuremirror.namedays.GetSavedNameDays
import pl.mobilespot.futuremirror.namedays.NameDaysRepository
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val nameDaysRepository: NameDaysRepository,
    private val savedStateHandle: SavedStateHandle,
    private val getSavedNameDays: GetSavedNameDays
) : ViewModel() {
    companion object {
        const val UI_STATE = "dashboard_ui_state"
    }

    val uiState = savedStateHandle.getStateFlow(UI_STATE, DashboardState.raw)

    init {
        val stateUi: DashboardState? = savedStateHandle[UI_STATE]

        Timber.d("Init ViewModel, StateUi: $stateUi")
        updateNameDay()
        viewModelScope.launch {
            getSavedNameDays.invoke().also {
                Timber.d("Loaded: ${it.count()} name days: $it")
            }
        }
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

