package pl.mobilespot.futuremirror.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mobilespot.futuremirror.news.usecase.GetNews
import javax.inject.Inject

const val NEWS_SOURCE = "bbc-news"

@HiltViewModel
class NewsViewModel @Inject constructor(getNews: GetNews) : ViewModel() {

    val allNews = getNews(listOf(NEWS_SOURCE)).cachedIn(viewModelScope)

}
