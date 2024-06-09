package pl.mobilespot.futuremirror.news.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pl.mobilespot.futuremirror.news.remote.NewsApi
import pl.mobilespot.futuremirror.news.remote.NewsPagingSource
import pl.mobilespot.futuremirror.news.remote.dto.Article
import javax.inject.Inject

const val PAGE_SIZE = 10

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }
}
