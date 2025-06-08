package com.phongbaoto.stormbookv2.utils.UtilsComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbookv2.data.model.auth.Role
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.plus

@Composable
fun HeaderComponent(
    isBack: Boolean = true,
    title: String,
    rightIcon: Int? = null,
    navController: NavController,
    role: Role? = Role.USER,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = Black)
    ) {
        // Icon back luôn nằm bên trái
        if (isBack) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "back",
                    tint = White
                )
            }
        }

        // Title luôn nằm giữa
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = White,
            modifier = Modifier.align(Alignment.Center)
        )

        // Right icon (nếu có) nằm bên phải
        if (role == Role.ADMIN && rightIcon != null) {
            IconButton(
                onClick = {
                    onClick?.invoke()
                },
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(id = rightIcon),
                    contentDescription = "right icon",
                    tint = White
                )
            }
        }
    }
}

