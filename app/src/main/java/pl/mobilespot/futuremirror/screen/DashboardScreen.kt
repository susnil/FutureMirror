package pl.mobilespot.futuremirror.screen

import android.icu.text.DateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import pl.mobilespot.futuremirror.ui.theme.FutureMirrorTheme
import pl.mobilespot.futuremirror.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = hiltViewModel()) {
    Row {
        GetDate()
        Button(onClick = { /*TODO*/ }) {
            Text("Select day")
        }
        val days = (1..Calendar.getInstance()
            .getActualMaximum(Calendar.DAY_OF_MONTH)).map { it }
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(days.count(), itemContent = { item ->
                val color: Color = getColorForDay(days[item])
                Text(text = "${days[item]}", color = color)
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