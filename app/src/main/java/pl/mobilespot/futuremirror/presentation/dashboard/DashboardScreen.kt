package pl.mobilespot.futuremirror.presentation.dashboard

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.mobilespot.futuremirror.core.utils.noOp
import pl.mobilespot.futuremirror.core.utils.noOpSingleArg
import pl.mobilespot.futuremirror.designsystem.ui.padding
import pl.mobilespot.futuremirror.designsystem.ui.theme.FutureMirrorTheme
import pl.mobilespot.futuremirror.presentation.DailyCard
import pl.mobilespot.futuremirror.presentation.GetDate
import pl.mobilespot.futuremirror.presentation.isFutureDay

@Composable
fun DashboardScreen(
    uiState: DashboardState,
    selectDay: (Int) -> Unit,
    unselected: () -> Unit,
    days: List<DashboardDay>,
    emptySlots: Int
) {

    Column {
        SubHeader {
            GetDate()
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.small),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.padding.small)
        ) {
            items(emptySlots) {}
            items(days.count(), itemContent = { item ->
                DailyCard(
                    days[item].day,
                    isFutureDay(days[item].day),
                    isSelectedDay(uiState, days[item].day),
                    days[item].isWeekend,
                    Modifier.clickable { selectDay(days[item].day) },
                )
            })
        }
        SubHeader {
            Column {
                uiState.namesDay.forEach { name ->
                    Text(
                        name,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
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

@Composable
private fun isSelectedDay(
    uiState: DashboardState,
    day: Int
) = uiState.selectedDay == day

@Preview(showBackground = true)
@Composable
private fun DashboardScreenPreview() {
    FutureMirrorTheme {
        DashboardScreen(
            DashboardState.raw,
            noOpSingleArg,
            noOp,
            (1..31).toList().map { DashboardDay(it, false) },
            1)
    }
}
