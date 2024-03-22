package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pl.mobilespot.futuremirror.namedays.NameDaysRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val nameDaysRepository: NameDaysRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DashboardState(""))
    val uiState: StateFlow<DashboardState> = _uiState.asStateFlow()

    init {
        Timber.d("Init ViewModel")
        updateNameDay()
    }

    private fun updateNameDay() {
        val nameDays = nameDaysRepository.getNamesForDay(uiState.value.selectedDay)
        _uiState.update { uiState.value.copy(nameDay = nameDays) }
        Timber.d("Name days $nameDays")
    }

    fun selectDay(day: Int) {
        _uiState.update { uiState.value.copy(selectedDay = day) }
        Timber.d("selectDay $day")
        updateNameDay()
    }
}

data class DashboardState(
    val nameDay: String,
    val selectedDay: Int? = null
)