package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mobilespot.futuremirror.namedays.DayMonth
import pl.mobilespot.futuremirror.namedays.NameDaysRepository
import timber.log.Timber
import javax.inject.Inject
import java.time.LocalDate

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val nameDaysRepository: NameDaysRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    companion object {
        const val UI_STATE = "dashboard_ui_state"
    }

    val uiState = savedStateHandle.getStateFlow(UI_STATE, DashboardState.raw)

    init {
        val stateUi: DashboardState? = savedStateHandle[UI_STATE]

        Timber.d("Init ViewModel, StateUi: $stateUi")
        updateNameDay()
    }

    private fun updateNameDay() {
        val localDate = LocalDate.now()
        val dayMonth = DayMonth(uiState.value.selectedDay ?: localDate.dayOfMonth, localDate.monthValue)
        val nameDays = nameDaysRepository.getNamesForDay(dayMonth)
        savedStateHandle[UI_STATE] = uiState.value.copy(namesDay = nameDays)
        Timber.d("Name days $nameDays")
    }

    fun selectDay(day: Int) {
        savedStateHandle[UI_STATE] = uiState.value.copy(selectedDay = day)
        Timber.d("selectDay $day")
        updateNameDay()
    }
}

