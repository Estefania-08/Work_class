package com.example.workclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.workclass.ui.screens.HomeScreen
import com.example.workclass.ui.screens.MainMenuScreen
import com.example.workclass.ui.screens.TestScreen
import com.example.workclass.ui.theme.WorkClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            WorkClassTheme {
                ComposeMultiScreenApp()
               /*Column() {
                    Column() {
                        TextComposable("Roberto")
                        TextComposable()
                    }
                    Row() {
                        TextComposable()
                        TextComposable()

                    }
                    Column() {
                        Text(text = "Hello Word!")
                        Text(text = "Welcome to your first application")
                    }
                    Column {
                        ModifierExample2()
                        ModifierExample4()
                        CustomText()
                        picture()

                    }
                }*/
            }
        }
    }

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
            TextComposable("1")
            TextComposable("2")
            TextComposable("3")
            TextComposable("4")

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
    }
}

@Composable
fun ComposeMultiScreenApp(){
    val navController = rememberNavController()
    SetupNavGraph(navController= navController)
}

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "main_menu") {
    composable("main_menu"){ MainMenuScreen(navController)}
        composable("home_screen"){ HomeScreen(navController)}
        composable("test_screen"){ TestScreen(navController)}

    }
}






