package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PostCardComponent(id:Int, title:String, text:String, image:Int) {
    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)
    ) {

        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(image),
            contentDescription = "Imagen Random",
            contentScale = ContentScale.Crop

        )
        Text(
            text = text,
            //stringResource(R.string.sample_text2),
            textAlign = TextAlign.Justify,
            lineHeight = 10.sp,
            modifier = Modifier
                .padding(10.dp)
        )
    }


}

@Composable
fun PostCardCompactComponent(id:Int, title:String, text:String, image:Int) {
    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)
    ) {

        Row() {
            Image(
                modifier = Modifier
                    .width(80.dp)
                    .height(100.dp),
                painter = painterResource(image),
                contentDescription = "Imagen Random",
                contentScale = ContentScale.Crop
            )
            Column() {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp)
                )
                Text(
                    text = text,
                    textAlign = TextAlign.Justify,
                    lineHeight = 14.sp,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }


}