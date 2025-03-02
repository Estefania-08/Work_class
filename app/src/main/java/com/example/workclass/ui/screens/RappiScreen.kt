package com.example.workclass.ui.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.workclass.R

/*class RappiScreen {
}*/

@Composable
fun RappiScreen(navController: NavHostController) {
    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                TopBar()
                CategorySection()
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    color = Color.LightGray
                )
                RappiCard()
                PromoBanner()
            }
            BottomNavigationBar()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Texto de dirección
        Text(
            text = "P° De La Presa 245, Aguascalientes",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Casa", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                // Icono sin círculo de fondo
                Icon(
                    painter = painterResource(id = R.drawable.dwp),
                    contentDescription = "Chevron Down",
                    modifier = Modifier.size(24.dp),

                )
            }

            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.locacion),
                    contentDescription = "Location Icon"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Barra de búsqueda
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "¿Qué quieres hoy?",
                    color = Color.Gray
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, CircleShape) // fondo blanco y forma ovalada buscar
             // borde gris y ovalado buscar
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = Color.Gray // para el icono gris
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "¿Qué quieres hoy?",
                color = Color.Gray // Texto gris
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CategorySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoryItem(R.drawable.restaurantes, "Restaurantes", Color.Red.copy(alpha = 0.1f)) // color rojo para Restaurantes
            CategoryItem(R.drawable.sup, "Súper", Color.Green.copy(alpha = 0.1f)) // color verde para Súper
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoryItem(R.drawable.farmacia, "Farmacia", Color.LightGray.copy(alpha = 0.1f))
            CategoryItem(R.drawable.express, "Express" , Color.LightGray.copy(alpha = 0.1f))
            CategoryItem(R.drawable.tiendas, "Tiendas", Color.LightGray.copy(alpha = 0.1f))
            CategoryItem(R.drawable.rapfav, "RappiFa", Color.LightGray.copy(alpha = 0.1f))
        }
    }
}

@Composable
fun CategoryItem(icon: Int, title: String,  backgroundColor : Color ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) { // la alineación horizontal
        Box(
            modifier = Modifier
                .background(backgroundColor, RoundedCornerShape(16.dp)) // El Box
                .padding(horizontal = 16.dp, vertical = 8.dp), // para ajustar el padding
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) { //  centrar la imagen y el texto
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RappiCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.rappicard),
                contentDescription = "RappiCard",
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "RappiCard", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = "Disfrútalo hoy y págalo a meses", fontSize = 14.sp, color = Color.Gray)
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Forward",
                tint = Color.Black
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PromoBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // bordes
            .clip(RoundedCornerShape(8.dp)) // Recortar para que coincidan
    ) {
        Image(
            painter = painterResource(id = R.drawable.godinfest),
            contentDescription = "Godín Fest Background",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        BottomNavItem(R.drawable.inicio, "Inicio")
        BottomNavItem(R.drawable.ofertas, "Ofertas")
        BottomNavItem(R.drawable.favoritos, "Favoritos")
        BottomNavItem(R.drawable.cuenta, "Cuenta")
    }
}

@Composable
fun BottomNavItem(icon: Int, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = title,
            modifier = Modifier.size(24.dp) // para ajustar el tamaño de los iconos
        )
        Spacer(modifier = Modifier.height(8.dp)) // Espacio que hay entre icono y texto
        Text(text = title, fontSize = 12.sp)

    }
}
@Preview(showBackground = true)
@Composable
fun PreviewRappiScreen() {

}


