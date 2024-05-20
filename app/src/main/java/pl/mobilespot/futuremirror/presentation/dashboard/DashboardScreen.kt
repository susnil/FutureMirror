package pl.mobilespot.futuremirror.presentation.dashboard

import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.mobilespot.futuremirror.datastore.UserPreferences
import pl.mobilespot.futuremirror.designsystem.ui.padding
import pl.mobilespot.futuremirror.designsystem.ui.theme.FutureMirrorTheme
import pl.mobilespot.futuremirror.presentation.DailyCard
import pl.mobilespot.futuremirror.presentation.GetDate
import pl.mobilespot.futuremirror.presentation.isFutureDay
import java.time.DayOfWeek

@Composable
fun DashboardScreen(
    uiState: DashboardState,
    userPreferences: UserPreferences?,
    selectDay: (Int) -> Unit,
    unselected: () -> Unit
) {

    Column {
        SubHeader {
            GetDate()
        }
        val fromDay = if (userPreferences != null) {
            if (userPreferences.showCompleted) 1 else Calendar.getInstance()
                .get(Calendar.DAY_OF_MONTH)
        } else {
            1
        }


        val days = remember { getDaysOfMonth(fromDay) }
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.small)
        ) {
            items(days.count(), itemContent = { item ->
                DailyCard(
                    days[item],
                    isFutureDay(days[item]),
                    isSelectedDay(uiState, days[item]),
                    DayOfWeek.of((days[item] % 7)+1) == DayOfWeek.SUNDAY,
                    Modifier.clickable { selectDay(days[item]) },)
            })
        }
        SubHeader {
            Column {
                uiState.namesDay.forEach { name -> Text(name, color = MaterialTheme.colorScheme.onSurface) }
            }
        }

        uiState.selectedDay?.let {
            Button(onClick = { unselected() }) {
                Text(text = "Unselect")
            }
        }
    }
}

@Composable
fun SubHeader(content: @Composable () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        content()
    }

}

private fun getDaysOfMonth(fromDay: Int) = (fromDay..Calendar.getInstance()
    .getActualMaximum(Calendar.DAY_OF_MONTH)).map { it }

@Composable
private fun isSelectedDay(
    uiState: DashboardState,
    day: Int
) = uiState.selectedDay == day

@Preview(showBackground = true)
@Composable
private fun DashboardScreenPreview() {
    FutureMirrorTheme {
        DashboardScreen(DashboardState.raw, UserPreferences.raw, {}) {}
    }
}
