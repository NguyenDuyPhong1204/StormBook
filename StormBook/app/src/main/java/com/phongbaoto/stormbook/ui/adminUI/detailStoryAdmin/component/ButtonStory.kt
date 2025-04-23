package com.phongbaoto.stormbook.ui.adminUI.detailStoryAdmin.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ButtonStory(
    title: String,
    color: Color,
    onClick: () -> Unit,
    textColor: Color,
    fontWeight: FontWeight,
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    Button(
        modifier = Modifier
            .width(screenWidth/2)
            .height(50.dp),
        onClick = onClick,
        shape = RoundedCornerShape(0.dp),
        colors = ButtonColors(
            containerColor = color,
            contentColor = color,
            disabledContentColor = color,
            disabledContainerColor = color
        )
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            color = textColor,
            fontWeight = fontWeight
        )
    }
}