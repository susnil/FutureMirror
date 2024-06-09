package pl.mobilespot.futuremirror.presentation.news

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun NewsRoute(viewModel: NewsViewModel = hiltViewModel()) {
    val articles = viewModel.allNews.collectAsLazyPagingItems()
    NewsScreen(articles)
}
