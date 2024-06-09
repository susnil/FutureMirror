package pl.mobilespot.futuremirror.news.remote.dto

import pl.mobilespot.futuremirror.news.remote.dto.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
