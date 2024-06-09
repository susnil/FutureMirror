package pl.mobilespot.futuremirror.news.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pl.mobilespot.futuremirror.news.remote.dto.Article

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>


}
