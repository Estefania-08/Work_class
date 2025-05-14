package com.example.workclass


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.workclass.ui.screens.AccountsScreen
import com.example.workclass.ui.screens.AppScreen
import com.example.workclass.ui.screens.ComponentsScreen
import com.example.workclass.ui.screens.FavoriteAccountsScreen
import com.example.workclass.ui.screens.HomeScreen
import com.example.workclass.ui.screens.LoginScreen
import com.example.workclass.ui.screens.MainMenuScreen
import com.example.workclass.ui.screens.ManageAccountScreen
import com.example.workclass.ui.screens.TestScreen
import database.AppDatabase



import com.example.workclass.ui.theme.WorkClassTheme
import database.DatabaseProvider

class MainActivity : ComponentActivity() {
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try{
            database = DatabaseProvider.getDatabase(this)
            Log.d("debug-db", "Database loaded successfully")

        }catch (exception: Exception){
            Log.d("debug-db", "ERROR: $exception")
        }
        setContent {
            WorkClassTheme {
                ComposeMultiScreenApp()
            }
        }
    }
/*
    @Preview(showBackground = true)
    @Composable
    fun TextComposable(name: String = "Empty") {
        Text("Helow World")
        Text(name)
    }

    @Preview(showBackground = true)
    @Composable
    fun Modifierexample1(name: String = "Empty") {
        Column(
            modifier = Modifier
                .padding(24.dp, 10.dp, 20.dp, 10.dp)
        ) {
            Text("Hellow World")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ModifierExample2() {
        Column(
            modifier = Modifier
                .padding(24.dp, 10.dp, 20.dp, 10.dp)
                .fillMaxWidth()
                .clickable(onClick = { clickAction() })
        ) {
            Text("Hellow World")

        }
    }
    fun clickAction(){
        println("Column Clicked")
    }

    @Preview(showBackground = true)
    @Composable
    fun ModifierExample3(){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.Gray)
                .border(width = 2.dp, color = Color.Black)
                .width(300.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ){
          //  TextComposable("1")
            //TextComposable("2")
            //TextComposable("3")
            //TextComposable("4")

        }
    }
    @Preview(showBackground = true) //12 de febrero
    @Composable
    fun ModifierExample4() {
        Box(
            modifier = Modifier
                .background(Color.Cyan)
                .padding(10.dp)
                .height(200.dp)
                .width(300.dp)
        ) {
            Text("1", Modifier.align(Alignment.TopStart))
            Text("2", Modifier.align(Alignment.TopCenter))
            Text("3", Modifier.align(Alignment.TopEnd))
            Text("4", Modifier.align(Alignment.CenterStart))
            Text("5", Modifier.align(Alignment.Center))
            Text("6", Modifier.align(Alignment.CenterEnd))
            Text("7", Modifier.align(Alignment.BottomStart))
            Text("8", Modifier.align(Alignment.BottomCenter))
            Text("9", Modifier.align(Alignment.BottomEnd))

        }

    }
}

@Preview(showBackground = true)
@Composable
fun CustomText() {
    Column() {
        Text(
            stringResource(R.string.sample_text),
            color = colorResource(R.color.teal_700),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
        val gradiantColors = listOf(Color.Cyan, Color.Blue, Color.Red, Color.Green, Color.LightGray, Color.Green)
        Text(
            stringResource(R.string.sample_text),
            style = TextStyle(brush = Brush.linearGradient(colors = gradiantColors))
        )
    }
}
@Preview(showBackground = true)
@Composable
fun picture(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .height(300.dp)
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(R.drawable.si),
            contentDescription = "Logo Android",
            contentScale = ContentScale.FillHeight
        )
    }*/
}

@Composable
fun ComposeMultiScreenApp(){
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
}

 @Composable
  fun SetupNavGraph(navController: NavHostController) {

     NavHost(navController = navController, startDestination = "main_menu") {
         composable("main_menu") { MainMenuScreen(navController) }
         composable("home_screen") { HomeScreen(navController) }
         composable("test_screen") { TestScreen(navController) }
         composable("components_screen") { ComponentsScreen(navController) }
         composable("login_screen") { LoginScreen(navController) }
         composable("accounts_screen") { AccountsScreen(navController) }
         composable("manage_account_screen") { ManageAccountScreen(navController) }
         composable(
             route = "manage_account_screen/{Id}",
             arguments = listOf(navArgument("id") { defaultValue = -1 })
         ) { backStackEntry ->
             val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
             ManageAccountScreen(
                 navController = navController,
                 accountId = id // nombrarlo
             )
         }
         composable("favorite_accounts_screen") { FavoriteAccountsScreen(navController) }
         composable("Contactos") { AppScreen(navController) }
     }
  }









