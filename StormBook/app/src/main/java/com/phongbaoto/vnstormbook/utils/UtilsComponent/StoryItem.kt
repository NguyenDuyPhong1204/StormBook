package com.phongbaoto.vnstormbook.utils.UtilsComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.vnstormbook.data.model.Story
import com.phongbaoto.vnstormbook.ui.theme.Black
import com.phongbaoto.vnstormbook.ui.theme.White

@Composable
fun StoryItem(
    width: Dp,
    height: Dp,
    story: Story,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = White, shape = RoundedCornerShape(10.dp))
            .background(color = Black)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(story.image),
            contentDescription = "image story",
            modifier = Modifier
                .fillMaxWidth()
                .height(145.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = story.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = White
        )
        Space(5.dp)
        Text(
            text = "Chương ${story.chapterNumber}",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = White
        )

    }
}
