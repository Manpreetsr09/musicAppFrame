//package man.run2.musicappui
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.Card
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//
//
//
//    @Composable
//
//    fun Browse() {
//        val categories = listOf<String>("Hits","Happy","Workout","Running","Tgif","yoga")
//
//
//    }
//
//
//    @Composable
//    fun browseCard(cat : String, drawable : Int){
//        Card(modifier = Modifier
//            .padding(16.dp)
//            .size(200.dp),border = BorderStroke(3.dp,color= Color.DarkGray)
//        ) {
//            Column (verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally){
//                Text(text = cat)
//                Image(painter = painterResource(id = drawable),
//                    contentDescription = cat)
//            }
//        }
//}