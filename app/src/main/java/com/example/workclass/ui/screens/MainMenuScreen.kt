package com.example.workclass.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
@Composable
fun MainMenuScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Main Menu",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Lista de botones con tamaño consistente
        val buttonModifier = Modifier
            .fillMaxWidth(0.6f)
            .height(48.dp)

        val buttonShape = RoundedCornerShape(18.dp) // Bordes menos redondeados

        MenuButton(
            modifier = buttonModifier,
            shape = buttonShape,
            text = "Home Screen",
            onClick = { navController.navigate("home_screen") }
        )

        MenuButton(
            modifier = buttonModifier,
            shape = buttonShape,
            text = " Test Screen",
            onClick = { navController.navigate("test_screen") }
        )

        MenuButton(
            modifier = buttonModifier,
            shape = buttonShape,
            text = "Components Screen",
            onClick = { navController.navigate("components_screen") }
        )

        MenuButton(
            modifier = buttonModifier,
            shape = buttonShape,
            text = "Login Screen",
            onClick = { navController.navigate("login_screen") }
        )

        MenuButton(
            modifier = buttonModifier,
            shape = buttonShape,
            text = "Accounts Screen",
            onClick = { navController.navigate("accounts_screen") }
        )

        MenuButton(
            modifier = buttonModifier,
            shape = buttonShape,
            text = "Camera Screen",
            onClick = { navController.navigate("Camara") }
        )

        MenuButton(
            modifier = buttonModifier,
            shape = buttonShape,
            text = "Calendar",
            onClick = { navController.navigate("Calendar") }
        )

        MenuButton(
            modifier = buttonModifier,
            shape = buttonShape,
            text = " Biometric",
            onClick = { navController.navigate("biometric_screen") }
        )
    }
}

@Composable
fun MenuButton(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(4.dp),
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.error
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}