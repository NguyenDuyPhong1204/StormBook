package com.phongbaoto.vnstormbook.ui.adminUI.storyAdmin.component

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.phongbaoto.vnstormbook.data.model.Story
import com.phongbaoto.vnstormbook.utils.UtilsComponent.ListWithPagination
import com.phongbaoto.vnstormbook.utils.UtilsComponent.StoryItem

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun StoryList(
    listStory: List<Story>,
    onClickItem: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val minItemWidth = screenWidth / 3.5f
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(minItemWidth),
//        horizontalArrangement = Arrangement.spacedBy(10.dp),
//        verticalArrangement = Arrangement.spacedBy(10.dp),
//        modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = screenHeight/6.5f)
//    ) {
//        items(listStory) { item ->
//            StoryItem(
//                width = minItemWidth,
//                height = 200.dp,
//                story = item
//            )
//        }
//    }
    ListWithPagination(
        list = listStory,
        itemsPerPage = 10,
        onPageChange = {page ->
            //
        },
        paddingBottom = 60.dp,
        itemComponent = { item ->
            StoryItem(
                width = minItemWidth,
                height = 200.dp,
                story = item,
                onClick = onClickItem
            )
        }
    )
}