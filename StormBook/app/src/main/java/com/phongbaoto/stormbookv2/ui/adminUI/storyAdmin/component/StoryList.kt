package com.phongbaoto.stormbookv2.ui.adminUI.storyAdmin.component

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.phongbaoto.stormbookv2.data.model.Story
import com.phongbaoto.stormbookv2.navigation.ROUTER
import com.phongbaoto.stormbookv2.utils.UtilsComponent.ListWithPagination
import com.phongbaoto.stormbookv2.utils.UtilsComponent.StoryItem

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun StoryList(
    navController: NavController,
    listStory: List<Story>,
    onClickItem: () -> Unit ={}
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val minItemWidth = screenWidth / 4f
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
        paddingBottom = 10.dp,
        itemComponent = { item ->
            StoryItem(
                width = 165.dp,
                height = 230.dp,
                story = item,
                onClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("previousTab", 2)
                    navController.navigate("${ROUTER.DetailStory.name}/${item.id}/false"){
                        launchSingleTop = true
                    }
                }
            )
        }
    )
}