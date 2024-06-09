package pl.mobilespot.futuremirror.news.remote

import pl.mobilespot.futuremirror.core.utils.Constants.API_KEY
import pl.mobilespot.futuremirror.news.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}
