package com.phongbaoto.stormbookv2.ui.detailStory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.phongbaoto.stormbookv2.BuildConfig
import com.phongbaoto.stormbookv2.data.model.Chapter
import com.phongbaoto.stormbookv2.data.model.Comment
import com.phongbaoto.stormbookv2.data.model.Story
import com.phongbaoto.stormbookv2.navigation.ROUTER
import com.phongbaoto.stormbookv2.ui.adminUI.detailStoryAdmin.component.InfoStory
import com.phongbaoto.stormbookv2.ui.adminUI.detailStoryAdmin.component.IntroduceStory
import com.phongbaoto.stormbookv2.ui.detailStory.component.CommentComponent
import com.phongbaoto.stormbookv2.ui.detailStory.component.ListChapterStory
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.BlueButton_3
import com.phongbaoto.stormbookv2.ui.theme.GreenButton
import com.phongbaoto.stormbookv2.ui.theme.RedButton_3
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.UtilsComponent.button.ButtonComponent
import com.phongbaoto.stormbookv2.utils.UtilsComponent.Space
import com.phongbaoto.stormbookv2.utils.banner
import com.phongbaoto.stormbookv2.utils.like
import com.phongbaoto.stormbookv2.utils.love
import com.phongbaoto.stormbookv2.utils.story
import com.phongbaoto.stormbookv2.viewmodel.storyViewModel.StoryUiState
import com.phongbaoto.stormbookv2.viewmodel.storyViewModel.StoryViewModel

@Composable
fun DetailStory(
    navController: NavController,
    isShowButton: Boolean,
    storyId: Long,
    viewModel: StoryViewModel = hiltViewModel()
) {
//    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val url = BuildConfig.BASE_URL

    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    //
    val storyById by viewModel.storyById.collectAsState()
    val listChapter by viewModel.listChapter.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getStoryById(storyId)
        viewModel.getChapterByStoryId(storyId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(bottom = 50.dp)
            .verticalScroll(scrollState)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // Bỏ hiệu ứng khi click
            ) {
                // Khi click vào (bất kỳ đâu trên màn hình), hủy tất cả focus
                focusManager.clearFocus()
            }
    ) {
        //image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(url + storyById?.cover_image)
                    .crossfade(true)
                    .build(),
                contentDescription = "cover_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()

            )

            IconButton(
                onClick = {
                    navController.popBackStack()

                    val previousTab = navController
                        .previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<Int>("previousTab")
                },
                modifier = Modifier
                    .size(30.dp)
                    .offset(x = 0.dp, y = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "back",
                    modifier = Modifier.size(20.dp),
                    tint = White
                )
            }
        }
        //information story
        Space(5.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            storyById?.let { InfoStory(story = it) }
        }
        //button
        Space(10.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                //doc tu dau
                ButtonComponent(
                    title = "Đọc từ đầu",
                    color = GreenButton,
                    modifier = Modifier
                        .height(50.dp),
                    textColor = White,
                    image = story,
                    fontWeight = FontWeight.Medium,
                    isImage = true,
                    shape = 5.dp,
                    fontSize = 16.sp,
                    sizeIcon = 20.dp,
                    onClick = {
                        navController.navigate(ROUTER.ChapterScreen.name)
                    },
                )
                //theo doi
                ButtonComponent(
                    title = "Theo dõi",
                    color = RedButton_3,
                    modifier = Modifier
                        .height(50.dp),
                    textColor = White,
                    image = love,
                    fontWeight = FontWeight.Medium,
                    isImage = true,
                    shape = 5.dp,
                    fontSize = 16.sp,
                    sizeIcon = 20.dp,
                    onClick = {},
                )
            }
            //thich
            Space(10.dp)
            ButtonComponent(
                title = "Thích",
                color = BlueButton_3,
                modifier = Modifier
                    .height(50.dp)
                    .width(160.dp),
                textColor = White,
                image = like,
                fontWeight = FontWeight.Medium,
                isImage = true,
                shape = 5.dp,
                fontSize = 16.sp,
                sizeIcon = 20.dp,
                onClick = {},
            )
        }

        //gioi thieu
        Space(10.dp)
        storyById?.title?.let {
            IntroduceStory(
                nameStory = it
            )
        }

        //list chapter
        Space(10.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
//            ListChapterStory(
//                listChapter = listChapter,
//                navController = navController,
//                isShowButton = isShowButton
//            )
            when (listChapter) {
                is StoryUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is StoryUiState.ListChapter -> {
                    ListChapterStory(
                        listChapter = (listChapter as StoryUiState.ListChapter).data,
                        navController = navController,
                        isShowButton = isShowButton
                    )
                }

                is StoryUiState.Error -> {
                    Text(text = (listChapter as StoryUiState.Error).message)
                }

                else -> {}
            }

            //comment
            Space(10.dp)
//        CommentComponent(
//            listComment = listComment
//        )

        }
    }
}