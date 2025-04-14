package components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(title: String, navController: NavHostController, location: String ) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            if (location == "accounts_screen") {
                IconButton(onClick = { navController.navigate("favorite_accounts_screen") }) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = "Icon")
                }
                IconButton(onClick = { navController.navigate("manage_account_screen") }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Icon")
                }
            }
        }
    )
}

