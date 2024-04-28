package pl.mobilespot.futuremirror.presentation.days

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DayDetailsScreen(viewModel: DayDetailsViewModel = hiltViewModel()) {
    val selectedDayId by viewModel.selectedDayId.collectAsStateWithLifecycle()
    Column {
        Text(text = "Selected: $selectedDayId")
        (1..2).forEach {
            Button(onClick = { viewModel.onDayClick(it.toString()) }) {
                Text(text = "Select: $it")
            }
        }
    }
}