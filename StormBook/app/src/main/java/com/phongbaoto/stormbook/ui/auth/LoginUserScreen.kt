package com.phongbaoto.stormbook.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.utils.UtilsComponent.BoxComponent
import com.phongbaoto.stormbook.utils.image_hello
import com.phongbaoto.stormbook.utils.login

@Composable
fun LoginUserScreen(navigation: NavController){
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    //thong tin dang nhap
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(top = 50.dp, start = 10.dp, end = 10.dp)
            .navigationBarsPadding()
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Column{
                Spacer(modifier = Modifier.padding(10.dp))
                BoxComponent(title = login)
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
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin(){
    val navigation = rememberNavController()
    LoginUserScreen(navigation)
}