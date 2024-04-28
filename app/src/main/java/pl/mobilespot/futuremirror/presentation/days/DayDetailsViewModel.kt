package pl.mobilespot.futuremirror.presentation.days

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import pl.mobilespot.futuremirror.presentation.navigation.Route.DayDetailsScreen.DAY_ID_ARG
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DayDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val selectedDayId: StateFlow<String?> = savedStateHandle.getStateFlow(DAY_ID_ARG, null)

    init {
        Timber.d("Selected: ${selectedDayId.value}")
    }

    fun onDayClick(dayId: String?) {
        savedStateHandle[DAY_ID_ARG] = dayId
    }
}