package man.run2.musicappui


import android.os.Bundle

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import man.run2.musicappui.ui.theme.MainView
import man.run2.musicappui.ui.theme.MusicAppUiTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicAppUiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }
}

