package com.phongbaoto.stormbook.ui.auth

import android.annotation.SuppressLint
import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.ui.auth.component.GoToRegister
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.BlueButton
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.UtilsComponent.BoxComponent
import com.phongbaoto.stormbook.utils.UtilsComponent.ButtonComponent
import com.phongbaoto.stormbook.utils.UtilsComponent.DividerWithText
import com.phongbaoto.stormbook.utils.UtilsComponent.Space
import com.phongbaoto.stormbook.utils.UtilsComponent.TextFieldComponent
import com.phongbaoto.stormbook.utils.UtilsComponent.WelcomeComponent
import com.phongbaoto.stormbook.utils.google
import com.phongbaoto.stormbook.utils.image_hello
import com.phongbaoto.stormbook.utils.login

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun LoginUserScreen(navController: NavController){
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    //thong tin dang nhap
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isFocusedEmail by remember { mutableStateOf(false) }
    val isFocusedPass by remember { mutableStateOf(false) }
    //


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(top = 50.dp, start = 15.dp, end = 15.dp)
            .navigationBarsPadding()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            WelcomeComponent(title = login)
            //email
            TextFieldComponent(
                value = email,
                onValueChange = {valueEmail ->
                    email = valueEmail
                },
                placeholder = "Vui lòng nhập email!",
                isFocused = remember { mutableStateOf(isFocusedEmail) }
            )
            //password
            Space(7.dp)
            TextFieldComponent(
                value = password,
                onValueChange = {valuePassword ->
                    password = valuePassword
                },
                placeholder = "Vui lòng nhập mật khẩu!",
                isFocused = remember { mutableStateOf(isFocusedPass)},
                isPassword = true
            )

            //quen mat khau
            Space(5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                Text(
                    text = "Quên mật khẩu ?",
                    fontSize = 16.sp,
                    color = White,
                    textAlign = TextAlign.End
                )
            }

            //button dang nhap
            Space(20.dp)
            ButtonComponent(
                title = "Đăng nhập",
                onClick = {},
                color = BlueButton,
                textColor = White,
                image = null,
                isImage = false,
                fontWeight = FontWeight.SemiBold
            )

            //hoac
            Space(15.dp)
            DividerWithText("Hoặc")

            Space(15.dp)
            ButtonComponent(
                title = "Đăng nhập bằng Google",
                onClick = {},
                color = White,
                textColor = Black,
                image = google,
                isImage = true,
                fontWeight = FontWeight.SemiBold
            )

            //khong co tai khoan
            Space(20.dp)
            GoToRegister(navController = navController)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin(){
    val navigation = rememberNavController()
    LoginUserScreen(navigation)
}