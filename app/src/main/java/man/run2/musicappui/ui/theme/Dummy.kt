package man.run2.musicappui.ui.theme

import androidx.annotation.DrawableRes
import man.run2.musicappui.R

data class Lib(@DrawableRes val icon : Int,val name : String)

val libraries = listOf<Lib>(
    Lib(R.drawable.ic_music,"Playlist"),
    Lib(R.drawable.baseline_person_add_alt_1_24,"Artist"),
    Lib(R.drawable.ic_account,"Album"),
    Lib(R.drawable.ic_launcher_foreground,"songs"),
    Lib(R.drawable.baseline_person_add_alt_1_24,"Gener")

)