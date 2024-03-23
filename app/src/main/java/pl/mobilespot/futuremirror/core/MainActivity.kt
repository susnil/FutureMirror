package pl.mobilespot.futuremirror.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import pl.mobilespot.futuremirror.designsystem.ui.theme.FutureMirrorTheme
import pl.mobilespot.futuremirror.presentation.navigation.NavGraph
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
                        NavGraph()
                    }
                }
            }
        }
        mySingleton.doSomething()
    }
}