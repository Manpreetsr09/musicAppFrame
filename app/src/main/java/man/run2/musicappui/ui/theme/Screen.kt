package man.run2.musicappui.ui.theme

import androidx.annotation.DrawableRes
import man.run2.musicappui.R

sealed class Screen(
    val title : String,
    val route : String) {

sealed class BottomScreen(
    val bTitle : String,
    val bRoute : String, @DrawableRes val icon: Int
):Screen(bTitle,bRoute){
    object Home : BottomScreen ("Home","home", androidx.core.R.drawable.notification_bg)
    object Library : BottomScreen("Library","library", androidx.core.R.drawable.ic_call_answer_low)
    object Browse : BottomScreen("Browse","browse", androidx.core.R.drawable.ic_call_answer_video_low)
}
    sealed class DrawerScreen(
    val dTitle : String,
    val dRoute :String,
    @DrawableRes
    val icon :Int)
        :Screen(dTitle,dRoute){
            object Account : DrawerScreen(
                "Account",
                "account",
                R.drawable.ic_account
            )
            object Subscription : DrawerScreen(
                "Subscription",
                "subscription",
                R.drawable.ic_subscribe
            )
        object AddAccount : DrawerScreen(
            "Add_Account",
            "add_account",
            R.drawable.baseline_person_add_alt_1_24
        )


    }

}

val screenInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Library,
    Screen.BottomScreen.Browse
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)