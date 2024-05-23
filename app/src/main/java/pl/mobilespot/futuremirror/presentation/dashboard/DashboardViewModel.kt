package pl.mobilespot.futuremirror.presentation.dashboard

import android.icu.util.Calendar
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
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

    private val settings: StateFlow<UserPreferences?> = userPreferencesRepository.userPreferences
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            null,
        ).also { Timber.d("user settings: ${it.value}") }
    private val dayFrom
        get() = settings.map {
            if (it != null) {
                if (it.showCompleted) 1 else Calendar.getInstance()
                    .get(Calendar.DAY_OF_MONTH)
            } else {
                1
            }
        }
    val dayOfMoths: StateFlow<List<Int>> = dayFrom.map { getDaysOfMonth(it) }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList(),
    )

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

private fun getDaysOfMonth(fromDay: Int) = (fromDay..Calendar.getInstance()
    .getActualMaximum(Calendar.DAY_OF_MONTH)).map { it }
