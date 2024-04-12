package pl.mobilespot.futuremirror.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pl.mobilespot.futuremirror.namedays.DayMonth

@Composable
fun SearchScreen(dayMonths: List<DayMonth> = emptyList()) {
    Column {
        Text(text = "Name days:")
        dayMonths.forEach {
            Text(text = "Day: ${it.day}, month: ${it.month}")
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    val list = listOf(DayMonth(1, 2), DayMonth(3, 4))

    SearchScreen(list)
}