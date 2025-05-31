package com.phongbaoto.stormbookv2.ui.adminUI.detailStoryAdmin.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.phongbaoto.stormbookv2.ui.theme.RedButton
import com.phongbaoto.stormbookv2.ui.theme.White

@Composable
fun ListCategoryStory(
    listCategory: List<Category>
) {
    val chunkedList = listCategory.chunked(3) // chia danh sách thành các nhóm 3 phần tử

    Column {
        chunkedList.forEach { rowItems ->
            Row {
                rowItems.forEach { item ->
                    ItemCategory(
                        category = item,
                    )
                }

                // Nếu không đủ 3 phần tử thì thêm spacer để lấp đầy hàng
                if (rowItems.size < 3) {
                    repeat(3 - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
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
            disabledContainerColor = Black,
            contentColor = Black,
            containerColor = Black,
            disabledContentColor = Black
        ),
        border = BorderStroke(width = 2.dp, color = RedButton),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 5.dp)
            .width(110.dp),
    ) {
        Text(
            text = category.name,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = White
        )
    }
}