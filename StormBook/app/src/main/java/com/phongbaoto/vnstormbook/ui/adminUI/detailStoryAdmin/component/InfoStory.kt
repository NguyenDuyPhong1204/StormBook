package com.phongbaoto.vnstormbook.ui.adminUI.detailStoryAdmin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.vnstormbook.data.model.Category
import com.phongbaoto.vnstormbook.ui.theme.Black
import com.phongbaoto.vnstormbook.ui.theme.White
import com.phongbaoto.vnstormbook.utils.UtilsComponent.Space
import com.phongbaoto.vnstormbook.utils.eye_story
import com.phongbaoto.vnstormbook.utils.like
import com.phongbaoto.vnstormbook.utils.love
import com.phongbaoto.vnstormbook.utils.user
import com.phongbaoto.vnstormbook.utils.wifi

@Composable
fun InfoStory(){
    val listCategory = listOf(
        Category(1, "Manhua"),
        Category(2, "Manhwa"),
        Category(3, "Action"),
        Category(4,"Lmao")
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black)
    ){
        //ten truyen
        Text(
            text = "Ta là Tà Đế",
            color = White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        //tac gia
        RowContent(
            icon = user,
            title = "Tác giả: ",
            content= "Đang cập nhật",
            width = 120.dp
        )
        //tinh trang
        RowContent(
            icon = wifi,
            title = "Tình trạng: ",
            content = "Đang cập nhật",
            width = 120.dp
        )
        //luot thich
        RowContent(
            icon = like,
            title = "Lượt thích: ",
            content = 5678.toString(),
            width = 120.dp
        )
        //luot theo doi
        RowContent(
            icon = love,
            title = "lượt theo dõi: ",
            content = 5678.toString(),
            width = 120.dp
        )
        //luot theo doi
        RowContent(
            icon = eye_story,
            title = "Lượt xem: ",
            content =  45678.toString(),
            width = 120.dp
        )

        ListCategoryStory(listCategory)

    }
}

//Row Content
@Composable
fun RowContent(
    icon: Int,
    title: String,
    content: String,
    width: Dp
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(width)
        ){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Icon",
                modifier = Modifier.size(20.dp),
                tint = White
            )
            Space(5.dp)
            Text(
                text = title,
                color = White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Space(20.dp)
        Text(
            text = content,
            color = White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )

    }
}