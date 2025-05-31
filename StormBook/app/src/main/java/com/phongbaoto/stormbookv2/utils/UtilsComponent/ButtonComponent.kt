package com.phongbaoto.stormbookv2.utils.UtilsComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonComponent(
    title: String,
    color: Color,
    onClick: () -> Unit,
    textColor: Color,
    image: Int?,
    isImage: Boolean = false,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier,
    shape: Dp = 10.dp,
    sizeIcon: Dp = 30.dp,
    fontSize: TextUnit = 18.sp
){
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(shape),
        colors = ButtonColors(
            containerColor = color,
            contentColor = color,
            disabledContentColor = color,
            disabledContainerColor = color
        )
    ) {

        if(isImage){
            image?.let { painterResource(it) }?.let {
                Image(
                    modifier = Modifier
                        .size(sizeIcon),
                    painter = it,
                    contentDescription = "Image"
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }

        Text(
            text = title,
            fontSize = fontSize,
            color = textColor,
            fontWeight = fontWeight
        )
    }
}

