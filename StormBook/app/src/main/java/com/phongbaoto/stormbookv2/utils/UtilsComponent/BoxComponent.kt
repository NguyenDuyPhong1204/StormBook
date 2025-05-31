package com.phongbaoto.stormbookv2.utils.UtilsComponent

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.hello
import com.phongbaoto.stormbookv2.utils.hello2
import com.phongbaoto.stormbookv2.utils.login

@Composable
fun BoxComponent(title: String){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Box(
       modifier = Modifier
           .background(color = Black)
           .offset(x = 10.dp, y = 2.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .background(White, shape = RoundedCornerShape(12.dp))
                    .padding(12.dp)
                    .width(screenWidth / 2f)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = hello,
                        fontSize = 13.sp,
                        color = Black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = hello2,
                        fontSize = 13.sp,
                        color = Black,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = title,
                        fontSize = 13.sp,
                        color = Black,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Mũi tên bên phải
                Canvas(
                    modifier = Modifier
                        .width(30.dp)
                        .height(15.dp)
                        .offset(x = 0.dp, y = 15.dp)
                ) {
                    val path = Path().apply {
                        moveTo(0f, 0f)  // Điểm trên cùng
                        lineTo(size.width, size.height / 2)  // Đỉnh tam giác
                        lineTo(0f, size.height)  // Điểm dưới cùng
                        close()
                    }
                    drawIntoCanvas {
                        drawPath(path, color = White)
                    }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBox(){
    BoxComponent(login)
}