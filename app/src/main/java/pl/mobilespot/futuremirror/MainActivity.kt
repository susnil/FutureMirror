package pl.mobilespot.futuremirror

import android.icu.text.DateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import pl.mobilespot.futuremirror.ui.theme.FutureMirrorTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mySingleton: SimpleObject
    override fun onCreate(savedInstanceState: Bundle?) {
        adaptSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            FutureMirrorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetDate()
                }
            }
        }
        mySingleton.doSomething()
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
fun GreetingPreview() {
    FutureMirrorTheme {
        GetDate()
    }
}