package pl.mobilespot.futuremirror.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import pl.mobilespot.futuremirror.namedays.NameDay

@Composable
fun SearchScreen(
    dayMonths: List<NameDay> = emptyList(), textValue: String = "",
    onTextChanged: (String) -> Unit = {}
) {

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = textValue))
    }

    Column {
        TextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                textFieldValue = newValue
                onTextChanged(newValue.text)
            }
        )
        Text(text = "Name days:")
        dayMonths.forEach {
            Text(text = "Name: ${it.name} day: ${it.day} month: ${it.month}")
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    val list = listOf(NameDay.raw)

    SearchScreen(list)
}