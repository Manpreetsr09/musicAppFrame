package man.run2.musicappui.ui.theme

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface

import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import man.run2.musicappui.AccountDialog
import man.run2.musicappui.MainViewModel
import man.run2.musicappui.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView(){

    val scaffoldState : ScaffoldState = rememberScaffoldState()
    val scope:CoroutineScope= rememberCoroutineScope()
    val viewModel : MainViewModel = viewModel()
val isSheetFullScreen by remember { mutableStateOf(false) }

val modifier = if(isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
//     allow us tofind out on which view we r at currently
    val controller: NavController= rememberNavController()
val navBackStateEntry by controller.currentBackStackEntryAsState()
val currentRoute = navBackStateEntry?.destination?.route
val dialogOpen = remember {
    mutableStateOf(false)
}
    val currentScreen = remember{
        viewModel.currentScreen.value
    }
    val title = remember{
        mutableStateOf(currentScreen.title)
    }
    val modalSheetState = androidx.compose.material.rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {it != ModalBottomSheetValue.HalfExpanded}
    )
    val roundedCornerRadius = if(isSheetFullScreen) 0.dp else 12.dp

val bottomBar :@Composable () -> Unit = {
    if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home) {
        BottomNavigation(Modifier.wrapContentSize()) {
            screenInBottom.forEach { item ->
                val isSelected = currentRoute == item.bRoute
                Log.d(
                    "Navigation",
                    "Item:${item.bTitle}, Current Route: $currentRoute , Is Selected"
                )

                val tint = if (isSelected) Color.White else Color.Black

                BottomNavigationItem(
                    selected = currentRoute == item.bRoute,
                    onClick = { controller.navigate(item.bRoute)
                        title.value = item.bTitle
                              }, icon = {
                        Icon(
                            tint = tint,
                            contentDescription = item.bTitle,
                            painter = painterResource(id = item.icon)
                        )
                    },
                    label = { Text(text = item.bTitle, color = tint) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Black
                )
            }
        }
    }
}
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
        sheetContent = {
        MoreBottomSheet(modifier = modifier)
    }) {


    Scaffold(
        bottomBar = bottomBar,
        topBar = {
            TopAppBar(title = { Text(title.value)},
                actions = {
                          IconButton(onClick =  {
                              scope.launch {
                                  if (modalSheetState.isVisible)
                                      modalSheetState.hide()
                                  else
                                      modalSheetState.show()
                              }
                          }){
                              Icon(imageVector = Icons.Default.MoreVert,
                                  contentDescription = null )
                          }
                },
                navigationIcon = {
                    IconButton(onClick ={
//                        open the drawer
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")
                    } }
                )
        },
        scaffoldState= scaffoldState,
        drawerContent = {
            LazyColumn(Modifier.padding(16.dp)){
                items(screensInDrawer){
                    item ->
                    DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        if (item.dRoute =="add_account"){
                        dialogOpen.value = true
                        }else{
                            controller.navigate(item.dRoute)

                            title.value = item.dTitle
                        }
                    }
                }
            }
        }
    ) {

        Navigation(navController = controller , viewModel = viewModel, pd =it )

    AccountDialog(dialogOpen = dialogOpen)
    }
}}
@Composable
fun DrawerItem(
    selected : Boolean,
    item : Screen.DrawerScreen,
    onDrawerItemClicked :() -> Unit
) {
    val background = if (selected) Color.DarkGray else Color.White
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawerItemClicked()
            }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.h5,
        )
    }
}
@Composable
fun MoreBottomSheet(modifier: Modifier) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colors.primarySurface)
    ){
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row (modifier= Modifier.padding(16.dp)){
                Icon(modifier = Modifier.padding(end=8.dp),
                    painter = painterResource(id = R.drawable.settings_24),
                    contentDescription = "Setting")
                Text(text = "Setting", fontSize = 20.sp, color = Color.White)
            }
            Row (modifier= Modifier.padding(16.dp)){
                Icon(modifier = Modifier.padding(end=8.dp),
                    painter = painterResource(id = R.drawable.settings_24),
                    contentDescription = "Share")
                Text(text = "Share", fontSize = 20.sp, color = Color.White)
            }
            Row (modifier= Modifier.padding(16.dp)){
                Icon(modifier = Modifier.padding(end=8.dp),
                    painter = painterResource(id = R.drawable.settings_24),
                    contentDescription = "Help")
                Text(text = "Help", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}
@Composable
fun Navigation (navController: NavController,viewModel: MainViewModel,pd :PaddingValues){
    NavHost(navController = navController as NavHostController,
        startDestination =Screen.DrawerScreen.AddAccount.route ,
        modifier = Modifier.padding(pd)){

        composable(Screen.BottomScreen.Home.route){
            Home()
        }
        composable(Screen.BottomScreen.Browse.route){
            Browse()
        }
        composable(Screen.BottomScreen.Library.route){
            Library()
        }
        composable(Screen.DrawerScreen.Account.route){
        AccountView()

        }
        composable(Screen.DrawerScreen.Subscription.route){
       Subscription()
        }
    }
}
