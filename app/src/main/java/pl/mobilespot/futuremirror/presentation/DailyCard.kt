package pl.mobilespot.futuremirror.presentation

import android.annotation.SuppressLint
import android.icu.text.DateFormat
import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import pl.mobilespot.futuremirror.designsystem.ui.padding

@Composable
fun DailyCard(
    day: Int,
    isFutureDay: Boolean,
    isSelectedDay: Boolean = false,
    isSunday: Boolean,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Card(
        modifier,
        colors = CardDefaults.cardColors(containerColor = (if (isSelectedDay) Color.Magenta else Color.Unspecified))
    ) {
        Row(
            modifier = Modifier.padding(MaterialTheme.padding.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.padding.medium)
        ) {
            Text(text = "$day", color = getColorForDay(isFutureDay, isSunday))
        }
    }
}

@Preview
@Composable
fun FutureDailyCardPreview(@PreviewParameter(DayProvider::class) day: Int) {
    DailyCard(day, isSunday = false, isFutureDay = false)
}

@Preview
@Composable
fun PastDailyCardPreview(@PreviewParameter(DayProvider::class, limit = 1) day: Int) {
    DailyCard(day, isSunday = false, isFutureDay = true)
}

class DayProvider : PreviewParameterProvider<Int> {
    override val values = sequenceOf(1, 31)
}

@Composable
fun GetDate() {
    val calendar = Calendar.getInstance().time
    val dataFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar)
    Text(dataFormat)
}

fun isFutureDay(
    day: Int
) = day > Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

fun getColorForDay(isFutureDay: Boolean, isSunday: Boolean) = if (isFutureDay) {
    Color.Gray
} else if (isSunday) Color.Red else Color.Black
