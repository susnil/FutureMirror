package pl.mobilespot.futuremirror.presentation.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import pl.mobilespot.futuremirror.R
import pl.mobilespot.futuremirror.designsystem.ui.padding

@Composable
fun TextEditDialog(
    @StringRes name: Int,
    storedValue: String,
    onSave: (String) -> Unit,
    onCheck: (String) -> Boolean,
    onDismiss: () -> Unit
) {

    var currentInput by remember {
        mutableStateOf(TextFieldValue(storedValue))
    }

    var isValid by remember {
        mutableStateOf(onCheck(storedValue))
    }

    Surface(
        color = MaterialTheme.colorScheme.surfaceTint
    ) {

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(MaterialTheme.padding.medium)
        ) {
            Text(stringResource(id = name))
            Spacer(modifier = Modifier.height(MaterialTheme.padding.small))
            TextField(currentInput, onValueChange = {
                isValid = onCheck(it.text)
                currentInput = it
            })
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                    onSave(currentInput.text)
                    onDismiss()
                    // disable / enable the button
                }, enabled = isValid) {
                    Text(stringResource(id = R.string.next))
                }
            }
        }
    }
}