package com.example.workclass.ui.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import components.TopBarComponent
import data.model.AccountModel
import data.viewmodel.AccountViewModel

@Composable
fun ManageAccountScreen(
    navController: NavController,
    accountId: Int? = null,
    viewModel: AccountViewModel = viewModel()
) {
    val account = remember { mutableStateOf(AccountModel()) }
    val context = LocalContext.current

    LaunchedEffect(accountId) {
        accountId?.let {
            viewModel.getAccount(it) { response ->
                if (response.isSuccessful) {
                    account.value = response.body() ?: AccountModel()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
            .fillMaxSize()
    ) {
        TopBarComponent("Account Management", navController as NavHostController, "manage_account_screen")

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.name,
            maxLines = 1,
            onValueChange = { account.value = account.value.copy(name = it) },
            label = { Text("Account Name") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.secondary
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.username,
            maxLines = 1,
            onValueChange = { account.value = account.value.copy(username = it) },
            label = { Text("Account Username") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.secondary
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.password,
            maxLines = 1,
            onValueChange = { account.value = account.value.copy(password = it) },
            label = { Text("Account Password") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.secondary
            )

        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = account.value.description,
            maxLines = 1,
            onValueChange = { account.value = account.value.copy(description = it) },
            label = { Text("Account Description") },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = Color.Transparent,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedTextColor = MaterialTheme.colorScheme.secondary
            )

        )

        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp),
            onClick = {
                TryCreateUpdateAccount(account, context, viewModel, accountId, navController)
            }
        ) {
            Text("Save/Update Account")
        }

        FilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp),
            onClick = {
                TryDeleteAccount(accountId, context, viewModel, navController)
            }
        ) {
            Text(text = "Delete Account")
        }
    }

}

fun TryCreateUpdateAccount(
    accountState: MutableState<AccountModel>,
    context: Context,
    viewModel: AccountViewModel,
    accountId: Int?,
    navController: NavController
) {
    try {
        val acc = accountState.value
        if (
            acc.name.isEmpty() ||
            acc.username.isEmpty() ||
            acc.password.isEmpty() ||
            acc.description.isEmpty()
        ) {
            Log.d("debug", "ERROR")
            Toast.makeText(
                context,
                "None of the fields can be empty",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (accountId == null) {
            viewModel.createAccount(acc) { jsonResponse ->
                val createAcStatus = jsonResponse?.get("store")?.asString
                Log.d("debug", "Create account status: $createAcStatus")
                if (createAcStatus == "success") {
                    Toast.makeText(
                        context,
                        "Account created successfully",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(
                        context,
                        "Error creating account",
                        Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            viewModel.updateAccount(accountId, acc) { jsonResponse ->
                val updateAcStatus = jsonResponse?.get("update")?.asString
                Log.d("debug", "Update account status: $updateAcStatus")
                if (updateAcStatus == "success") {
                    Toast.makeText(
                        context,
                        "Account updated successfully",
                        Toast.LENGTH_SHORT)
                        .show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(
                        context,
                        "Error updating account",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    } catch (exception: Exception) {
        Log.d("debug", "ERROR: $exception")
    }
}

fun TryDeleteAccount(
    accountId: Int?,
    context: Context,
    viewModel: AccountViewModel,
    navController: NavController
) {
    if (accountId == null) {
        Toast.makeText(context, "No account to delete", Toast.LENGTH_SHORT).show()
        return
    }

    viewModel.deleteAccount(accountId) { jsonResponse ->
        val deleteStatus = jsonResponse?.get("delete")?.asString
        Log.d("debug", "Delete account status: $deleteStatus")

        if (deleteStatus == "success") {
            Toast.makeText(
                context,
                "Account deleted successfully",
                Toast.LENGTH_SHORT
            ).show()
            navController.popBackStack()
        } else {
            Toast.makeText(
                context,
                "Error deleting account",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
