package pl.mobilespot.futuremirror.presentation.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import kotlinx.coroutines.launch
import pl.mobilespot.futuremirror.designsystem.ui.theme.dimen16
import kotlin.math.roundToInt

@Composable
fun NewsScreen() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    var scrollToPosition  by remember { mutableFloatStateOf(0F) }

    Column(Modifier.verticalScroll(scrollState)) {
        repeat(50) {
            Text(
                text = "Pre Item $it",
                modifier = Modifier
                    .padding(dimen16)
                    .clickable {
                        coroutineScope.launch {
                            scrollState.animateScrollTo(scrollToPosition.roundToInt())
                        }
                    }
            )
        }
        Text("Found me!",
            modifier = Modifier.onGloballyPositioned { coordinates ->
                scrollToPosition = coordinates.positionInRoot().y
            })
        repeat(55) {
            Text(text = "Post item $it")
        }
    }
}
