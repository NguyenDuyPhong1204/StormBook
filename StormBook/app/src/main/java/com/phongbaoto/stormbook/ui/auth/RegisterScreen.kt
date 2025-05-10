package com.phongbaoto.stormbook.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.ui.auth.component.GoToLogin
import com.phongbaoto.stormbook.ui.auth.component.WelcomeComponent
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.BlueButton
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.UtilsComponent.ButtonComponent
import com.phongbaoto.stormbook.utils.UtilsComponent.Space
import com.phongbaoto.stormbook.utils.UtilsComponent.TextFieldComponent
import com.phongbaoto.stormbook.utils.register

@Preview
@Composable
fun PreviewRegister(){
    RegisterScreen(rememberNavController())
}

@Composable
fun RegisterScreen(navController: NavController){
    val configuration = LocalConfiguration.current
    //thong tin dang ky
    //email
    var email by remember { mutableStateOf("") }
    var isFocusedEmail by remember { mutableStateOf(false) }
    //name or nickname
    var name by remember { mutableStateOf("") }
    var isFocusedName by remember { mutableStateOf(false) }
    //password
    var password by remember { mutableStateOf("") }
    var isFocusedPass by remember { mutableStateOf(false) }
    //confirm password
    var confirmPassword by remember { mutableStateOf("") }
    var isFocusedConfirmPass by remember { mutableStateOf(false) }
    //
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(top = 50.dp, start = 15.dp, end = 15.dp)
            .navigationBarsPadding()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ){
                focusManager.clearFocus()
            }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            WelcomeComponent(
                title = register
            )
            //email
            TextFieldComponent(
                value = email,
                onValueChange = {valueEmail ->
                    email = valueEmail
                },
                placeholder = "Vui lòng nhập email!",
                isFocused = remember { mutableStateOf(isFocusedEmail) }
            )
            //name
            Space(7.dp)
            TextFieldComponent(
                value = name,
                onValueChange = {valueName ->
                    name = valueName
                },
                placeholder = "Vui lòng nhập tên hoặc biệt danh của bạn!",
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
            //confirm password
            Space(7.dp)
            TextFieldComponent(
                value = confirmPassword,
                onValueChange = {valueConfirmPassword ->
                    confirmPassword = valueConfirmPassword
                },
                placeholder = "Vui lòng nhập lại mật khẩu!",
                isFocused = remember { mutableStateOf(isFocusedConfirmPass)},
                isPassword = true
            )
            //button dang ky
            Space(20.dp)
            ButtonComponent(
                title = "Đăng ký",
                onClick = {},
                color = BlueButton,
                textColor = White,
                image = null,
                isImage = false,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Space(20.dp)
            GoToLogin(navController)

        }
    }

}