package pl.mobilespot.futuremirror.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import pl.mobilespot.futuremirror.namedays.local.data.NameDay
import pl.mobilespot.futuremirror.testing.CommonTags.SEARCH_NAME

@Composable
fun SearchScreen(
    dayMonths: List<NameDay> = emptyList(), textValue: String = "",
    onTextChanged: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        var textFieldValue by remember {
            mutableStateOf(TextFieldValue(text = textValue))
        }

        Column {
            TextField(
                value = textFieldValue,
                onValueChange = { newValue ->
                    textFieldValue = newValue
                    onTextChanged(newValue.text)
                },
                modifier = Modifier.semantics { testTag = SEARCH_NAME }
            )
            Text(text = "Name days:")
            dayMonths.forEach {
                Text(text = "Name: ${it.name} day: ${it.day} month: ${it.month}")
            }
            if (dayMonths.isEmpty()) {
                Text(text = "No result")
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    val list = listOf(NameDay.raw)

    SearchScreen(list)
}
