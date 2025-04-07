package com.example.workclass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import components.TopBarComponent
import data.model.AccountModel
import data.viewmodel.AccountViewModel

@Composable
fun ManageAccountScreen(
    navController: NavHostController,
    viewModel: AccountViewModel = viewModel()
    ){
    val account = remember { mutableStateOf(AccountModel()) }
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
            .fillMaxSize()
    ){
        TopBarComponent("Add Account", navController, "manage_account_screen")

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.name,
            maxLines = 1,
            label = { "Account Name"},
            onValueChange = {
                account.value = account.value.copy(name = it)
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.name,
            maxLines = 1,
            label = { "Account Name"},
            onValueChange = {
                account.value = account.value.copy(name = it)
            }

        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.name,
            maxLines = 1,
            label = { "Account Password"},
            onValueChange = {
                account.value = account.value.copy(name = it)
            }

        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.name,
            maxLines = 1,
            label = { "Account Description"},
            onValueChange = {
                account.value = account.value.copy(name = it)
            }
        )
        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding( 0.dp, 10.dp),
            onClick = {

            }
        ) {
            Text("Save Account")
        }
    }
    //hacer que funcione put y delete
}