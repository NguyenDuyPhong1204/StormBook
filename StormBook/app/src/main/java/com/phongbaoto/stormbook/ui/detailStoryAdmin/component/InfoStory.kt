package com.phongbaoto.stormbook.ui.detailStoryAdmin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.stormbook.data.model.Category
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.UtilsComponent.Space
import com.phongbaoto.stormbook.utils.eye_story
import com.phongbaoto.stormbook.utils.like
import com.phongbaoto.stormbook.utils.love
import com.phongbaoto.stormbook.utils.person
import com.phongbaoto.stormbook.utils.user
import com.phongbaoto.stormbook.utils.wifi

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
            content= "Đang cập nhật"
        )
        //tinh trang
        RowContent(
            icon = wifi,
            title = "Tình trạng: ",
            content = "Đang cập nhật"
        )
        //luot thich
        RowContent(
            icon = like,
            title = "Lượt thích: ",
            content = 5678.toString()
        )
        //luot theo doi
        RowContent(
            icon = love,
            title = "lượt theo dõi: ",
            content = 5678.toString()
        )
        //luot theo doi
        RowContent(
            icon = eye_story,
            title = "Lượt xem: ",
            content =  45678.toString()
        )

        ListCategoryStory(listCategory)

    }
}

//Row Content
@Composable
fun RowContent(
    icon: Int,
    title: String,
    content: String
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
                .width(120.dp)
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