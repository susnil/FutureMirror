package pl.mobilespot.futuremirror.presentation.dashboard

import androidx.lifecycle.SavedStateHandle
import org.junit.Before
import org.junit.Test
import pl.mobilespot.futuremirror.namedays.NameDaysDataSource
import pl.mobilespot.futuremirror.namedays.NameDaysRepository

class DashboardViewModelTest {

    private lateinit var viewModel: DashboardViewModel

    @Before
    fun setup() {
        val savedState =
            SavedStateHandle(mapOf(DashboardViewModel.UI_STATE to DashboardState(emptyList(), 1)))
        viewModel = DashboardViewModel(NameDaysRepository(NameDaysDataSource()), savedState)
    }

    @Test
    fun `init viewmodel should return list of names day`() {
        assert(viewModel.uiState.value.namesDay.isNotEmpty())
    }
}