package com.phongbaoto.vnstormbook.ui.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.vnstormbook.ui.theme.White

@Composable
fun SearchComponent(
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp)
            .offset(x = 0.dp, y = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = "Đề xuất",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = White
        )

        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(25.dp)
        ){
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = White
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearch(){
    SearchComponent(
        onClick = {}
    )
}
