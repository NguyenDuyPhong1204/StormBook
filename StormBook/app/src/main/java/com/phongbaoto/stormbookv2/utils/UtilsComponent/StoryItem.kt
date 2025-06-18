package com.phongbaoto.stormbookv2.utils.UtilsComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.phongbaoto.stormbookv2.BuildConfig
import com.phongbaoto.stormbookv2.data.model.Chapter
import com.phongbaoto.stormbookv2.data.model.Story
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.viewmodel.storyViewModel.StoryUiState
import com.phongbaoto.stormbookv2.viewmodel.storyViewModel.StoryViewModel

@Composable
fun StoryItem(
    width: Dp,
    height: Dp,
    story: Story,
    onClick: () -> Unit
){
    val context = LocalContext.current
    val url = BuildConfig.BASE_URL
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
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(url + story.cover_image)
                .crossfade(true)
                .build(),
            contentDescription = "cover_image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
        )
        Space(5.dp)
        Text(
            text = story.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 2.dp)
        )
        Space(5.dp)
//        Text(
//            text = "Chương $lastChapter",
//            fontSize = 14.sp,
//            fontWeight = FontWeight.SemiBold,
//            color = White
//        )

    }
}
