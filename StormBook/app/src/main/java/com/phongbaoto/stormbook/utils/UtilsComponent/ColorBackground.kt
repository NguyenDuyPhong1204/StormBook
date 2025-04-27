package com.phongbaoto.stormbook.utils.UtilsComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.phongbaoto.stormbook.ui.theme.Black

@Composable
fun ColorBackground(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFF0909), // đỏ sáng hơn
                        Color(0xFF9C0606), // đỏ đậm
                        Black
                    ),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    )
}