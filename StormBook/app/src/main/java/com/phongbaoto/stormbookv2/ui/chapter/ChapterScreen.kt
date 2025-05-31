package com.phongbaoto.stormbookv2.ui.chapter

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.phongbaoto.stormbookv2.ui.chapter.data.ListFake
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.utils.UtilsComponent.HeaderComponent
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalFocusManager
import com.phongbaoto.stormbookv2.ui.chapter.component.DialogReport
import com.phongbaoto.stormbookv2.ui.chapter.component.NavigationChapter


@Composable
fun ChapterScreen(
    navController: NavController
) {
    val localHost = "http://10.0.2.2:8080"
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    //theo doi trang thai cua LazyColumn
    val listState = rememberLazyListState()
    //cuon len thi true xuong thi false
    var isScrollingUp by remember { mutableStateOf(true) }
    var lastScrollOffset by remember { mutableStateOf(0) }
    //
    var showReportDialog by remember { mutableStateOf(false) }
    Log.d("ShowDialog", "ChapterScreen: ${showReportDialog}")
    // Theo dõi scroll
    LaunchedEffect(listState.firstVisibleItemScrollOffset) {
        val currentOffset = listState.firstVisibleItemScrollOffset
        isScrollingUp = currentOffset < lastScrollOffset
        lastScrollOffset = currentOffset
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .navigationBarsPadding()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // Bỏ hiệu ứng khi click
            ) {
                // Khi click vào (bất kỳ đâu trên màn hình), hủy tất cả focus
                focusManager.clearFocus()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HeaderComponent(
                title = "Chương 1",
                onClick = {},
                navController = navController,
            )
            //danh sach anh cua chuong
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(ListFake.listImage) { image ->
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(localHost + image.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Image Chapter",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
        }
        if (showReportDialog) {
            DialogReport(
                showDialog = showReportDialog,
                onDismiss = {
                    showReportDialog = false
                }
            )
        }
        //thanh hoat dong cac thu
        AnimatedVisibility(
            visible = isScrollingUp,
            enter = fadeIn() + slideInVertically(
                initialOffsetY = { it }, // Slide từ dưới lên
                animationSpec = tween(durationMillis = 300)
            ),
            exit = fadeOut() + slideOutVertically(
                targetOffsetY = { it }, // Slide xuống dưới
                animationSpec = tween(durationMillis = 300)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .background(Black)
            ) {
                NavigationChapter(
                    onShowReportDialog = {
                        showReportDialog = true
                    }
                )
            }
        }
    }
}