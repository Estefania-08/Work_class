package com.example.workclass


import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.clasetrabajo.ui.screens.AppScreen
import com.example.clasetrabajo.ui.screens.Calendar
import com.example.workclass.ui.screens.AccountsScreen

import com.example.workclass.ui.screens.BiometricScreen

import com.example.workclass.ui.screens.Camara

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

class MainActivity : FragmentActivity() {
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

         composable("Calendar") { Calendar(navController) }
         composable ("biometric_screen"){
             val context = LocalContext.current
             BiometricScreen(navController, onAuthSuccess = {
                 Toast.makeText(context, "Authentication successful", Toast.LENGTH_SHORT).show()

             })
         }
         //composable("notification_screen"){ NotificationScreen(navController) }


         composable("CalScreen") { Calendar(navController) }

         composable("Camara") { Camara(navController) }


     }
  }










