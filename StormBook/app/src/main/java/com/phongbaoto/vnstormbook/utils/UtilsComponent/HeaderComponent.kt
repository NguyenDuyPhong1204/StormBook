package com.phongbaoto.vnstormbook.utils.UtilsComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import com.phongbaoto.vnstormbook.ui.theme.Black
import com.phongbaoto.vnstormbook.ui.theme.White
import com.phongbaoto.vnstormbook.utils.plus

@Composable
fun HeaderComponent(
    title: String,
    rightIcon: Int? =null,
    navController: NavController,
    onClick: (() -> Unit)? = null
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(color = Black),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "back",
                modifier = Modifier.size(20.dp),
                tint = White
            )
        }

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )

        rightIcon.let { icon ->
            IconButton(
                onClick = {
                    onClick?.invoke()
                }
            ) {
                rightIcon?.let { painterResource(it) }?.let {
                    Icon(
                        painter = it,
                        contentDescription = "right icon",
                        tint = White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeader(){
    val navController = rememberNavController()
    HeaderComponent(
        title = "Danh sách thể loại",
        rightIcon = plus,
        navController = navController
    )
}