package man.run2.musicappui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import man.run2.musicappui.ui.theme.Screen

class MainViewModel :ViewModel() {

    private val _currentScreen : MutableState<Screen> = mutableStateOf(Screen.DrawerScreen.AddAccount)

    val currentScreen : MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen){
        _currentScreen.value= screen
    }
}