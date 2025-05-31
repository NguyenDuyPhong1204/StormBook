package com.phongbaoto.stormbookv2.utils.UtilsComponent

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.stormbookv2.ui.theme.White

@Composable
fun DividerWithText(
    text: String,
    modifier: Modifier = Modifier,
    dividerColor: Color = White,
    textColor: Color = White
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier.weight(1f),
            color = dividerColor,
            thickness = 1.dp
        )
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = textColor,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )
        Divider(
            modifier = Modifier.weight(1f),
            color = dividerColor,
            thickness = 1.dp
        )
    }
}