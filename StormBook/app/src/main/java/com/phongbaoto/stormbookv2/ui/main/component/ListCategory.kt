package com.phongbaoto.stormbookv2.ui.main.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.stormbookv2.data.model.Category
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.White

@Composable
fun ListCategory(
    listCategory: List<Category>
){
    LazyRow(){
        items(listCategory){item ->
            ItemCategory(
                category = item
            )
        }
    }
}

@Composable
fun ItemCategory(
    category: Category
){
    Button(
        onClick = {},
        colors = ButtonColors(
            disabledContainerColor = White,
            contentColor = White,
            containerColor = White,
            disabledContentColor = White
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .width(110.dp),
    ) {
        Text(
            text = category.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            color = Black
        )
    }
}
