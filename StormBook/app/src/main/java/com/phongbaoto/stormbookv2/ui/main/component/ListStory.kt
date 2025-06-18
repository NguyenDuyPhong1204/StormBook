package com.phongbaoto.stormbookv2.ui.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.phongbaoto.stormbookv2.data.model.Chapter
import com.phongbaoto.stormbookv2.data.model.Story
import com.phongbaoto.stormbookv2.navigation.ROUTER
import com.phongbaoto.stormbookv2.utils.UtilsComponent.StoryItem
import com.phongbaoto.stormbookv2.viewmodel.chapterViewModel.ChapterViewModel
import com.phongbaoto.stormbookv2.viewmodel.storyViewModel.StoryUiState
import com.phongbaoto.stormbookv2.viewmodel.storyViewModel.StoryViewModel

@Composable
fun ListStory(
    listStory: List<Story>,
    navController: NavController,
    onClick: () -> Unit ={}
){

    val viewModel: StoryViewModel = hiltViewModel()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(listStory){item ->
            StoryItem(
                width = 165.dp,
                height = 230.dp,
                story = item,
                onClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("previousTab", 0)
                    navController.navigate("${ROUTER.DetailStory.name}/${item.id}/false"){
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}