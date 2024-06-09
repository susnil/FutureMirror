package pl.mobilespot.futuremirror.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import pl.mobilespot.futuremirror.namedays.usecase.GetSavedNameDays
import pl.mobilespot.futuremirror.namedays.local.data.SearchResult
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSavedNameDays: GetSavedNameDays
) : ViewModel() {

    private val currentSearchQuery = MutableStateFlow("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val result: StateFlow<SearchResult> =
        currentSearchQuery.debounce(250)
            .distinctUntilChanged()
            .filter { it.isNotBlank() }
            .mapLatest { query ->
                getSavedNameDays.findName(searchText = query)
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(300),
                initialValue = SearchResult()
            )

    fun setSearchingText(text: String) {
        Timber.d("Change input field: $text")
        currentSearchQuery.update { text }
    }
}
