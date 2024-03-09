package pl.mobilespot.futuremirror.viewmodel

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
    nameDaysRepository: NameDaysRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow("")
    val uiState: StateFlow<String> = _uiState.asStateFlow()

    init {
        Timber.d("Init ViewModel")
        val nameDays = nameDaysRepository.getNamesForDay()
        _uiState.update { nameDays }
        Timber.d("Name days $nameDays")
    }
}