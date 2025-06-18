package com.phongbaoto.stormbookv2.ui.ListStory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.phongbaoto.stormbookv2.data.model.Category
import com.phongbaoto.stormbookv2.ui.adminUI.storyAdmin.component.StoryList
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.utils.UtilsComponent.HeaderComponent
import com.phongbaoto.stormbookv2.utils.UtilsComponent.textField.SearchFieldComponent
import com.phongbaoto.stormbookv2.viewmodel.storyViewModel.StoryUiState
import com.phongbaoto.stormbookv2.viewmodel.storyViewModel.StoryViewModel

@Composable
fun StoryByCategoryScreen(
    navController: NavController,
    viewModel: StoryViewModel = hiltViewModel(),
    categoryId: Long,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val listStoryByCategory by viewModel.listByCategoryID.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        viewModel.getStoryByCategoryId(categoryId, 0, 10)
    }

    //bien name cho tim kiem
    var findNameStory by remember { mutableStateOf("") }
    //
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 30.dp, start = 15.dp, end = 15.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null // Bỏ hiệu ứng khi click
                ) {
                    // Khi click vào (bất kỳ đâu trên màn hình), hủy tất cả focus
                    focusManager.clearFocus()
                }
        ) {

            HeaderComponent(
                title = "Danh sách truyện",
                isBack = true,
                onClick = {
                    navController.popBackStack()
                },
                navController = navController
            )

            ///tim kiem
            SearchFieldComponent(
                value = findNameStory,
                onValueChange = {
                    findNameStory = it
                },
                placeholder = "Nhập tên truyện để tìm kiếm",
                isFocused = remember { mutableStateOf(isFocused) }
            )

            when (listStoryByCategory) {
                is StoryUiState.Loading -> {}
                is StoryUiState.Error -> Text((listStoryByCategory as StoryUiState.Error).message)
                is StoryUiState.ListByCategoryID -> {
                    val listStory = (listStoryByCategory as StoryUiState.ListByCategoryID).data
                    StoryList(
                        navController = navController,
                        listStory = listStory,
                        onClickItem = {}
                    )
                }

                else -> {}
            }

//
        }

    }

}