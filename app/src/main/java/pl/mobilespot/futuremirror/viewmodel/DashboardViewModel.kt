package pl.mobilespot.futuremirror.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mobilespot.futuremirror.namedays.NameDaysRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val nameDaysRepository: NameDaysRepository
) : ViewModel() {

    init {
        Timber.d("Init ViewModel")
        val nameDays = nameDaysRepository.getNamesForDay()
        Timber.d("Name days $nameDays")
    }
}