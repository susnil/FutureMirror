package pl.mobilespot.futuremirror.screen

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.mobilespot.futuremirror.ui.padding
import pl.mobilespot.futuremirror.ui.theme.FutureMirrorTheme
import pl.mobilespot.futuremirror.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    val nameDay by viewModel.uiState.collectAsStateWithLifecycle()

    Row {
        Column {
            GetDate()
            Text(nameDay)
        }
        val days = (1..Calendar.getInstance()
            .getActualMaximum(Calendar.DAY_OF_MONTH)).map { it }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 60.dp),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.small)
        ) {
            items(days.count(), itemContent = { item ->
                DailyCard(days[item], isFutureDay(days[item]))
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DashboardScreenPreview() {
    FutureMirrorTheme {
        DashboardScreen()
    }
}