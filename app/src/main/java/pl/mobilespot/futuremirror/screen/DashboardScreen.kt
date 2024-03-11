package pl.mobilespot.futuremirror.screen

import android.icu.text.DateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(days.count(), itemContent = { item ->
                Card {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        val color: Color = getColorForDay(days[item])
                        Text(text = "${days[item]}", color = color)
                    }
                }
            })
        }
    }
}

@Composable
fun GetDate() {
    val calendar = Calendar.getInstance().time
    val dataFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar)
    Text(dataFormat)
}

@Preview(showBackground = true)
@Composable
private fun DashboardScreenPreview() {
    FutureMirrorTheme {
        DashboardScreen()
    }
}

private fun getColorForDay(
    day: Int
) = if (day <= Calendar.getInstance()
        .get(Calendar.DAY_OF_MONTH)
) {
    Color.Black
} else Color.Gray