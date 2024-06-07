package pl.mobilespot.futuremirror.presentation.navigation

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pl.mobilespot.futuremirror.R
import pl.mobilespot.futuremirror.core.utils.noOpSingleArg
import pl.mobilespot.futuremirror.designsystem.ui.theme.FutureMirrorTheme
import pl.mobilespot.futuremirror.designsystem.ui.theme.dimen10
import pl.mobilespot.futuremirror.designsystem.ui.theme.dimen20
import pl.mobilespot.futuremirror.designsystem.ui.theme.dimen6

@Composable
fun BottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = dimen10
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(dimen20),
                        )
                        Spacer(modifier = Modifier.height(dimen6))
                        Text(text = stringResource(id = item.label), style = MaterialTheme.typography.labelSmall)
                    }
                },
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
)

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationPreview() {
    FutureMirrorTheme {
        BottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.ic_dashboard, label = R.string.dashboard),
            BottomNavigationItem(icon = R.drawable.ic_search, label = R.string.search),
            BottomNavigationItem(icon = R.drawable.ic_newspaper, label = R.string.news),
        ), selectedItem = 0, onItemClick = noOpSingleArg)
    }
}
