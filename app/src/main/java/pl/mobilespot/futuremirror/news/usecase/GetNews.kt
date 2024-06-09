package pl.mobilespot.futuremirror.news.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import pl.mobilespot.futuremirror.news.repository.NewsRepository
import pl.mobilespot.futuremirror.news.remote.dto.Article
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}
