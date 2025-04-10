package com.phongbaoto.stormbook.ui.category.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonDialog(
    title: String,
    color: Color,
    onClick: () -> Unit,
    textColor: Color,
    fontWeight: FontWeight,
    width: Dp,
    height: Dp
){
    Button(
        modifier = Modifier
            .width(width)
            .height(height),
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonColors(
            containerColor = color,
            contentColor = color,
            disabledContentColor = color,
            disabledContainerColor = color
        )
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            color = textColor,
            fontWeight = fontWeight
        )
    }
}