package com.phongbaoto.stormbook.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun PreForgotPass(
    navController: NavController,
    type: String? = null,
    email: String? = null
) {
    //email
    var email by remember { mutableStateOf(email ?: "") }
    var isFocusedEmail by remember { mutableStateOf(false) }
    //confirmCode
    var showConfirmCodeField by remember { mutableStateOf(false) }
    var confirmCode by remember { mutableStateOf("") }
    var isFocusedConfirmCode by remember { mutableStateOf(false) }
    //
    val focusManager = LocalFocusManager.current
    //text button
    var textHeader = when (type) {
        "forgotPass" -> "Quên mật khẩu"
        else -> "Đổi mật khẩu"
    }

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
    ) {

        HeaderComponent(
            title = textHeader,
            navController = navController
        )

        Space(20.dp)
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {
            //email
            TextFieldComponent(
                value = email,
                onValueChange = { valueEmail ->
                    email = valueEmail
                },
                placeholder = "Vui lòng nhập email!",
                isFocused = remember { mutableStateOf(isFocusedEmail) }
            )
            //confirm code
            if (showConfirmCodeField) {
                Space(7.dp)
                TextFieldComponent(
                    value = confirmCode,
                    onValueChange = { valueCode ->
                        confirmCode = valueCode
                    },
                    placeholder = "Vui lòng nhập mã xác nhận!",
                    isFocused = remember { mutableStateOf(isFocusedConfirmCode) }
                )
            }

            Space(15.dp)
//
            ButtonComponent(
                title = if (showConfirmCodeField) "Xác nhận" else "Gửi mã xác nhận",
                color = BlueButton,
                textColor = White,
                image = null,
                isImage = false,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    showConfirmCodeField = true
                }
            )
        }
    }
}