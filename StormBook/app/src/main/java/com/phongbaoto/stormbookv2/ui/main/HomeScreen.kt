package com.phongbaoto.stormbookv2.ui.main

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.phongbaoto.stormbookv2.navigation.ROUTER
import com.phongbaoto.stormbookv2.ui.main.component.BannedComponent
import com.phongbaoto.stormbookv2.ui.main.component.ListCategory
import com.phongbaoto.stormbookv2.ui.main.component.ListStory
import com.phongbaoto.stormbookv2.ui.main.component.SearchComponent
import com.phongbaoto.stormbookv2.ui.main.component.TitleComponent
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.utils.UtilsComponent.HideStatusBar
import com.phongbaoto.stormbookv2.utils.UtilsComponent.dialog.SearchDialog
import com.phongbaoto.stormbookv2.utils.UtilsComponent.Space
import com.phongbaoto.stormbookv2.utils.UtilsComponent.dialog.SuccessToast
import com.phongbaoto.stormbookv2.utils.background
import com.phongbaoto.stormbookv2.utils.hot
import com.phongbaoto.stormbookv2.utils.propose
import com.phongbaoto.stormbookv2.viewmodel.authViewModel.LoginViewModel
import com.phongbaoto.stormbookv2.viewmodel.categoryViewModel.CategoryUiState
import com.phongbaoto.stormbookv2.viewmodel.categoryViewModel.CategoryViewModel
import com.phongbaoto.stormbookv2.viewmodel.chapterViewModel.ChapterViewModel
import com.phongbaoto.stormbookv2.viewmodel.storyViewModel.StoryUiState
import com.phongbaoto.stormbookv2.viewmodel.storyViewModel.StoryViewModel
import kotlinx.coroutines.delay

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    storyViewModel: StoryViewModel = hiltViewModel(),
    chapterViewModel: ChapterViewModel = hiltViewModel(),
    onNavigateToCategory: () -> Unit = {},
    onNavigateToStory: () -> Unit = {},
    onNavigateToDetail: () -> Unit ={}
) {
    var showSuccess by remember { mutableStateOf(false) }
    val isLoginSuccess by viewModel.isLoginSuccess.collectAsState()

    val context = LocalContext.current
    val prefs = remember {
        context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
    }

    val isLogin = remember {
        mutableStateOf(prefs.getBoolean("isLoginSuccess", false))
    }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val scrollState = rememberScrollState()
    //
    var isShowDialog by remember { mutableStateOf(false) }
    //category
    val uiState by categoryViewModel.uiState.collectAsStateWithLifecycle()
    //story
    val uiStoryState by storyViewModel.uiState.collectAsStateWithLifecycle()
    val suggestedStories by storyViewModel.suggestedStories.collectAsStateWithLifecycle()
    val hotWeekStories by storyViewModel.hotWeekStories.collectAsStateWithLifecycle()
    LaunchedEffect(isLogin.value) {
        if (isLogin.value) {
            showSuccess = true
            delay(3000)
            showSuccess = false
            viewModel.clearLoginSuccess()
        }
    }

    HideStatusBar()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(color = Black)
            .verticalScroll(scrollState)
            .padding(bottom = screenHeight / 7.5f)
    ) {

        Image(
            painter = painterResource(background),
            contentDescription = "background",
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 2.5f)
                .offset(x = 0.dp, y = -35.dp)
        )
        SuccessToast(
            message = "Đăng nhập thành công",
            visible = showSuccess,
            onDismiss = {
                showSuccess = false
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp)
        ) {
            //tim kiem
            SearchComponent(
                onClick = {
                    isShowDialog = true
                }
            )

            //banner
            Space(15.dp)
            BannedComponent()

            //the loai
            Column {
                TitleComponent(
                    title = "Thể loại",
                    onClick = {
                       onNavigateToCategory()
                    },
                    isImage = false,
                    image = null
                )
                //category
                when (uiState) {
                    CategoryUiState.Loading -> {
//                        CircularProgressIndicator()
                    }

                    is CategoryUiState.Error -> {
                        Text((uiState as CategoryUiState.Error).message)
                    }

                    is CategoryUiState.Success -> {
                        ListCategory((uiState as CategoryUiState.Success).data, navController)
                    }
                }

            }

            //de xuat
            Column {
                TitleComponent(
                    title = "Đề xuất",
                    onClick = {
                        onNavigateToStory()
                    },
                    isImage = true,
                    image = propose
                )
                Space(5.dp)
//                ListStory(
//                    listStory,
//                    navController = navController
//                )

                when (suggestedStories) {
                    is StoryUiState.Loading -> {}
                    is StoryUiState.Error -> Text((suggestedStories as StoryUiState.Error).message)
                    is StoryUiState.SuggestedList -> {
                        val listStory = (suggestedStories as StoryUiState.SuggestedList).data
                        ListStory(listStory = listStory, navController = navController, onClick = onNavigateToDetail)
                    }
                    else -> {}
                }

            }

            //truyen hot
            Space(5.dp)
            Column {
                TitleComponent(
                    title = "Truyện Hot",
                    onClick = {
                        onNavigateToStory()
                    },
                    isImage = true,
                    image = hot
                )
                Space(5.dp)
                when (hotWeekStories) {
                    is StoryUiState.Loading -> {}
                    is StoryUiState.Error -> Text((hotWeekStories as StoryUiState.Error).message)
                    is StoryUiState.HotWeekList -> {
                        val listStory = (hotWeekStories as StoryUiState.HotWeekList).data
                        ListStory(listStory = listStory, navController = navController, onClick = onNavigateToDetail)
                    }
                    else -> {}
                }
            }
            if (isShowDialog) {
                SearchDialog(
                    showDialog = isShowDialog,
                    onDismiss = {
                        isShowDialog = false
                    }
                )
            }
        }
    }
}
