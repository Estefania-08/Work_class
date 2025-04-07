package com.example.workclass.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import data.model.UserModel
import data.viewmodel.UserViewModel


@Composable
fun LoginScreen(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        LoginForm(navController)
    }
}

@Composable
fun LoginForm(navController: NavHostController,
    viewModel: UserViewModel = viewModel()){
    val context = LocalContext.current

    Card (
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = MaterialTheme.colorScheme.surface
        ),
        modifier = Modifier
            .padding(40.dp, 0.dp)
    ){
        Column (
            modifier = Modifier
                .padding(20.dp)
        ){
            var user by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            AsyncImage(
                model = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fnah.wikipedia.org%2Fwiki%2F%25C4%25AAxiptli%3AHonda_logo.png&psig=AOvVaw1SvCYH-EYuN51zkw3CXg-M&ust=1743134561398000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCNj2qbSwqYwDFQAAAAAdAAAAABAE",
                contentDescription = "Logo Honda",
                contentScale = ContentScale.Fit

            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = user,
                maxLines = 1,
                onValueChange = {user = it}, // cuando se modifica el texto se sigue asignando valor variable user
                label = { Text("User")},
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = password,
                maxLines = 1,
                onValueChange = {password = it}, // cuando se modifica el texto se sigue asignando valor variable user
                label = { Text("Password")},
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                )
            )
            FilledTonalButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( 0.dp, 10.dp),
                shape = CutCornerShape(4.dp),
                onClick = { TryLogin(user, password, context, viewModel, navController) }
            ) {
                Text("LOG IN")
            }
            OutlinedButton (
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( 0.dp, 10.dp),
                shape = CutCornerShape(4.dp),
                onClick = { }
            ) {
                Text("CREATE ACCOUNT")
            }
        }
    }
}
fun TryLogin(
    user:String,
    password:String,
    context: Context,
    viewModel: UserViewModel,
    navController: NavController
    ){
    if(user == "" || password == "") {
        Toast.makeText(
            context,
            "User or Password cannot be empty",
            Toast.LENGTH_SHORT
        ).show()
    }else{
        val user_model = UserModel(0,"", user, password)
        viewModel.loginApi(user_model){ jsonResponse ->
            val loginStatus = jsonResponse?.get("login")?.asString
            Log.d("debug","Login Status: $loginStatus")
            if(loginStatus == "success"){
                navController.navigate("accounts_screen")
            }
        }
    }
}


