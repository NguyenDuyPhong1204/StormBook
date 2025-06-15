package com.phongbaoto.stormbookv2.ui.adminUI.detailStoryAdmin.component

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
import com.phongbaoto.stormbookv2.data.model.STATUS
import com.phongbaoto.stormbookv2.data.model.Story
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.UtilsComponent.Space
import com.phongbaoto.stormbookv2.utils.eye_story
import com.phongbaoto.stormbookv2.utils.like
import com.phongbaoto.stormbookv2.utils.love
import com.phongbaoto.stormbookv2.utils.user
import com.phongbaoto.stormbookv2.utils.wifi

@Composable
fun InfoStory(
    story: Story
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black)
    ){
        //ten truyen
        Text(
            text = story.title,
            color = White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        //tac gia
        RowContent(
            icon = user,
            title = "Tác giả: ",
            content= story.author,
            width = 120.dp
        )
        //tinh trang
        RowContent(
            icon = wifi,
            title = "Tình trạng: ",
            content =
            when(story.status.toString()){
                STATUS.OnGoing.toString() -> "Đang cập nhật"
                STATUS.Dropped.toString() -> "Dừng cập nhật"
                STATUS.Completed.toString() -> "Đã hoàn thành"
                else -> ""
            },
            width = 120.dp
        )
//        //luot thich
//        RowContent(
//            icon = like,
//            title = "Lượt thích: ",
//            content = story.,
//            width = 120.dp
//        )
//        //luot theo doi
//        RowContent(
//            icon = love,
//            title = "lượt theo dõi: ",
//            content = 5678.toString(),
//            width = 120.dp
//        )
        //luot theo doi
        RowContent(
            icon = eye_story,
            title = "Lượt xem: ",
            content = story.view_count.toString(),
            width = 120.dp
        )

//        ListCategoryStory(listCategory)

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