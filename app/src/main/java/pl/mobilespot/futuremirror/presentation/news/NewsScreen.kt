package pl.mobilespot.futuremirror.presentation.news

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import pl.mobilespot.futuremirror.designsystem.ui.theme.DarkRed
import pl.mobilespot.futuremirror.designsystem.ui.theme.dimen24
import pl.mobilespot.futuremirror.designsystem.ui.theme.dimen6
import pl.mobilespot.futuremirror.news.remote.dto.Article
import timber.log.Timber

@Composable
fun NewsScreen(articles: LazyPagingItems<Article>) {
    val context = LocalContext.current

    val handlePagingResult = handlePagingResult(articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimen24),
            contentPadding = PaddingValues(all = dimen6)
        ) {
            items(
                count = articles.itemCount,
            ) {
                articles[it]?.let { article ->
                    NewsCard(article = article, onClick = {
                        Timber.v("Open ${article.url}")
                        Intent(Intent.ACTION_VIEW).also {intent ->
                            intent.data = Uri.parse(article.url)
                            context.startActivity(intent)
                        }
                    })
                }
            }
        }
    }
}

@Composable
private fun handlePagingResult(articles: LazyPagingItems<Article>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            LinearProgressIndicator()
            false
        }

        error != null -> {
            Text(text = "Error", color = DarkRed)
            false
        }

        else -> {
            true
        }
    }
}
