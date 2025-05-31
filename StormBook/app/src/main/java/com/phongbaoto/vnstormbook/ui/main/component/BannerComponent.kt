package com.phongbaoto.vnstormbook.ui.main.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.phongbaoto.vnstormbook.ui.theme.RedIndicator
import com.phongbaoto.vnstormbook.ui.theme.White
import com.phongbaoto.vnstormbook.utils.banner
import com.phongbaoto.vnstormbook.utils.banner_2
import com.phongbaoto.vnstormbook.utils.banner_3
import com.phongbaoto.vnstormbook.utils.banner_4
import kotlinx.coroutines.delay

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun BannedComponent(){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val listImage = listOf(banner, banner_2, banner_3, banner_4)
    var currentIndex by remember { mutableStateOf(0) }
    val imageCount = listImage.size

    //di chuyen
    LaunchedEffect(key1 = currentIndex) {
        delay(3000)
        currentIndex = (currentIndex + 1) % imageCount
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight/3.5f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .aspectRatio(16 / 9f), // Tạo khung cố định theo tỷ lệ chuẩn (Ví dụ: 16:9)

        ) {
            Image(
                painter = painterResource(id = listImage[currentIndex]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop // Cắt ảnh cho vừa khung, giữ nguyên tỷ lệ
            )
        }
        //cham tron
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ){
            repeat(imageCount){index ->
                Indicator(isSelected = index == currentIndex)
            }
        }

    }
}

@Composable
fun Indicator(isSelected: Boolean){
    val color = if(isSelected) RedIndicator else White
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(6.dp)
            .clip(CircleShape)
            .background(color)
    )

}



