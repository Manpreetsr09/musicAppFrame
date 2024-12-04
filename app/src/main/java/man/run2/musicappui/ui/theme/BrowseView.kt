package man.run2.musicappui.ui.theme

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import man.run2.musicappui.R


@Composable

fun Browse() {
    val categories = listOf<String>("Hits","Happy","Workout","Running","Tgif","yoga")
LazyVerticalGrid(GridCells.Fixed(2)){
    items (categories){ cat ->
        BrowserItem(cat = cat, drawable = R.drawable.browser_updated_24)
    }
}

}
