package com.phongbaoto.stormbook.utils.UtilsComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.BlueButton
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.eye

@Composable
fun ButtonComponent(
    title: String,
    color: Color,
    onClick: () -> Unit,
    textColor: Color,
    image: Int?,
    isImage: Boolean = false,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier
){
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
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
                        .size(30.dp),
                    painter = it,
                    contentDescription = "Image"
                )
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            text = title,
            fontSize = 18.sp,
            color = textColor,
            fontWeight = fontWeight
        )
    }
}

