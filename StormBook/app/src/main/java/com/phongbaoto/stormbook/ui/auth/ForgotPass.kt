package com.phongbaoto.stormbook.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.BlueButton
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.UtilsComponent.ButtonComponent
import com.phongbaoto.stormbook.utils.UtilsComponent.HeaderComponent
import com.phongbaoto.stormbook.utils.UtilsComponent.Space
import com.phongbaoto.stormbook.utils.UtilsComponent.TextFieldComponent

@Composable
fun ForgotPass(
    navController: NavController,
    email: String
){
    //password
    var password by remember { mutableStateOf("") }
    var isFocusedPass by remember { mutableStateOf(false) }
    //confirm password
    var confirmPassword by remember { mutableStateOf("") }
    var isFocusedConfirmPass by remember { mutableStateOf(false) }
    //
    var focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                focusManager.clearFocus()
            }
    ){
        HeaderComponent(
            title = "Đặt lại mật khẩu",
            navController = navController
        )
        Space(15.dp)
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ){
            //new pass
            TextFieldComponent(
                value = password,
                onValueChange = { valuePass ->
                    password = valuePass
                },
                placeholder = "Vui lòng nhập mật khẩu mới!",
                isFocused = remember { mutableStateOf(isFocusedPass) },
                isPassword = true
            )
            //confirm pass
            Space(7.dp)
            TextFieldComponent(
                value = confirmPassword,
                onValueChange = { valuePassConfirm ->
                    confirmPassword = valuePassConfirm
                },
                placeholder = "Vui lòng nhập lại mật khẩu!",
                isFocused = remember { mutableStateOf(isFocusedConfirmPass) },
                isPassword = true
            )
            Space(15.dp)
            ButtonComponent(
                title = "Xác nhận",
                color = BlueButton,
                textColor = White,
                image = null,
                isImage = false,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {

                }
            )

        }
    }
}