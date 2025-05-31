package com.phongbaoto.stormbookv2.ui.chapter.data

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.phongbaoto.stormbookv2.ui.theme.White

@Composable
fun IconType.RenderIcon(
    modifier: Modifier = Modifier,
    tint: Color = White
) {
    when (this) {
        is IconType.Vector -> {
            Icon(
                imageVector = this.icon,
                contentDescription = "Icon",
                tint = tint,
                modifier = modifier
            )
        }
        is IconType.Resource -> {
            Icon(
                painter = painterResource(id = this.resId),
                contentDescription = "Icon",
                tint = tint,
                modifier = modifier
            )
        }
    }
}