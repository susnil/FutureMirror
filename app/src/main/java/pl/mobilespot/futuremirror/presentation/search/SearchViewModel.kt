package pl.mobilespot.futuremirror.presentation.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import pl.mobilespot.futuremirror.namedays.GetSavedNameDays
import pl.mobilespot.futuremirror.namedays.NameDay
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSavedNameDays: GetSavedNameDays) :
    ViewModel() {
    private var searchingText = ""
    fun findNames(): Flow<List<NameDay>> {
        return getSavedNameDays.findName(searchText = searchingText)
    }

    fun setSearchingText(text: String) {
        Timber.d("Change input field: $text")
        searchingText = text
        //todo do not trigger change for found list
    }
}