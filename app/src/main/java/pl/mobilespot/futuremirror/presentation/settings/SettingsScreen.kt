package pl.mobilespot.futuremirror.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import pl.mobilespot.futuremirror.R
import pl.mobilespot.futuremirror.designsystem.ui.padding
import pl.mobilespot.futuremirror.testing.CommonTags.NAMES_COUNTER

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(state: SettingsState, namesCount: Int, toggleSwitch: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.settings),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(MaterialTheme.padding.medium)
        ) {
            SettingsClickableComponent(
                name = R.string.title,
                icon = R.drawable.ic_icon,
                iconDesc = R.string.icon_description,
            ) {

            }
            SettingsSwitchComponent(
                name = R.string.title,
                icon = R.drawable.ic_icon,
                iconDesc = R.string.icon_description,
                state = state.boolean
            ) {
                toggleSwitch()
            }

            SettingsTextComponent(
                name = R.string.title,
                icon = R.drawable.ic_icon,
                iconDesc = R.string.icon_description,
                state = state.text,
                onSave = {}, onCheck = { _ -> true },
            )
            Row {
                Text(text = "Name day count: ")
                Text("$namesCount", modifier = Modifier.semantics { testTag = NAMES_COUNTER })
            }
        }
    }
}


@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(SettingsState.raw, 0)
}