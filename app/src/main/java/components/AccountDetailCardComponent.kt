package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.workclass.R

@Composable
fun AccountDetailCardComponent(
    id: Int,
    name: String,
    username: String,
    password: String,
    imageURL: String,
    description: String
){
    Column {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(10.dp),
            Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                model = imageURL,
                error = painterResource(R.drawable.si),
                contentDescription = "Account Logo",
                contentScale = ContentScale.FillBounds
            )
            Row(

            ){
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ){
                    Text(modifier = Modifier.padding(10.dp),
                        text = name,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(modifier = Modifier.padding(1.dp),
                        text= username,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light)
                    Text(modifier = Modifier.padding(1.dp),
                        text= password,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light)
                    Text(modifier = Modifier.padding(1.dp),
                        text= description,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }
       }
    }
}