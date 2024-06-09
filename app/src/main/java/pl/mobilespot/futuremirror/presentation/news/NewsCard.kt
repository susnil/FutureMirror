package pl.mobilespot.futuremirror.presentation.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pl.mobilespot.futuremirror.designsystem.ui.theme.Body
import pl.mobilespot.futuremirror.designsystem.ui.theme.Title
import pl.mobilespot.futuremirror.designsystem.ui.theme.dimen4
import pl.mobilespot.futuremirror.designsystem.ui.theme.dimen6
import pl.mobilespot.futuremirror.designsystem.ui.theme.dimen64
import pl.mobilespot.futuremirror.news.remote.dto.Article

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onClick?.invoke() },
    ) {
        AsyncImage(
            modifier = Modifier
                .size(dimen64)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = dimen6)
                .height(dimen64)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium.copy(),
                color = Title,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = Body
                )
                Spacer(modifier = Modifier.width(dimen4))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelSmall,
                    color = Body
                )
            }
        }
    }
}
