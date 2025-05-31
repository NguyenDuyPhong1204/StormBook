package com.phongbaoto.vnstormbook.ui.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.vnstormbook.ui.profile.data.Item
import com.phongbaoto.vnstormbook.ui.theme.White
import com.phongbaoto.vnstormbook.utils.UtilsComponent.Space

@Composable
fun MenuSelect(
    listItem: List<Item>
){
    LazyColumn(
        contentPadding = PaddingValues(5.dp)
    ){
        items(listItem){ item ->
            ItemMenu(
                item = item
            )
        }
    }
}

@Composable
fun ItemMenu(
    item: Item
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 5.dp)
            .background(color = Color.Transparent)
            .clickable {
                item.onClick
            },
        verticalArrangement = Arrangement.Center
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Transparent)
                .padding(horizontal = 2.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = "icon",
                    tint = White,
                    modifier = Modifier
                        .size(25.dp)
                )
                Space(5.dp)
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    color = White,
                    fontWeight = FontWeight.Medium
                )

            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = White,
            thickness = 1.dp
        )

    }
}