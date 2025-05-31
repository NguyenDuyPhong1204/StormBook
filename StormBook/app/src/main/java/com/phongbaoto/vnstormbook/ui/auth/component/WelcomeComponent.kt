package com.phongbaoto.vnstormbook.ui.auth.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.phongbaoto.vnstormbook.utils.UtilsComponent.BoxComponent
import com.phongbaoto.vnstormbook.utils.image_hello

@Composable
fun WelcomeComponent(title: String){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        Column{
            Spacer(modifier = Modifier.padding(10.dp))
            BoxComponent(title = title)
        }
        Image(
            painter = painterResource(id = image_hello),
            contentDescription = "image",
            modifier = Modifier
                .width(screenWidth/2.5f)
                .height(screenHeight/4.3f)
                .offset(x = -10.dp, y =0.dp)
        )
    }
}
