package com.phongbaoto.vnstormbook.ui.adminUI.detailStoryAdmin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.vnstormbook.ui.theme.Black
import com.phongbaoto.vnstormbook.ui.theme.White
import com.phongbaoto.vnstormbook.utils.UtilsComponent.Space
import com.phongbaoto.vnstormbook.utils.information

@Composable
fun IntroduceStory(
    nameStory: String
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(color = Black)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(information),
                contentDescription = "icon",
                tint = White,
                modifier = Modifier.size(25.dp)
            )
            Space(5.dp)
            Text(
                text = "Giới thiệu: ",
                color = White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Space(5.dp)
        Text(
            text = "Giới thiệu: Truyện “${nameStory}” được cập nhật nhanh và đầy đủ nhất tại StormBook." +
                    " Bạn đọc đừng quên để lại bình luận và chia sẻ, ủng hộ StormBook ra các chương mới " +
                    "nhất của truyện",
            color = White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}