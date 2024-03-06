package pl.mobilespot.futuremirror

import android.icu.text.DateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                Scaffold(Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
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
                }
            }
        }
        mySingleton.doSomething()
    }

    @Composable
    fun GetDate() {
        val calendar = Calendar.getInstance().time
        val dataFormat = DateFormat.getDateInstance(DateFormat.FULL).format(calendar)
        Text(dataFormat)
    }

    private fun getColorForDay(
        day: Int
    ) = if (day <= Calendar.getInstance()
            .get(Calendar.DAY_OF_MONTH)
    ) {
        Color.Black
    } else Color.Gray
}