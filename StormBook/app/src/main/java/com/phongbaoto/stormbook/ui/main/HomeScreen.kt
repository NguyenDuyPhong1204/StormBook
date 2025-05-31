package com.phongbaoto.stormbook.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.data.model.Category
import com.phongbaoto.stormbook.data.model.Story
import com.phongbaoto.stormbook.navigation.ROUTER
import com.phongbaoto.stormbook.ui.main.component.BannedComponent
import com.phongbaoto.stormbook.ui.main.component.ListCategory
import com.phongbaoto.stormbook.ui.main.component.ListStory
import com.phongbaoto.stormbook.ui.main.component.SearchComponent
import com.phongbaoto.stormbook.ui.main.component.TitleComponent
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.utils.UtilsComponent.HideStatusBar
import com.phongbaoto.stormbook.utils.UtilsComponent.SearchDialog
import com.phongbaoto.stormbook.utils.UtilsComponent.Space
import com.phongbaoto.stormbook.utils.UtilsComponent.SuccessToast
import com.phongbaoto.stormbook.utils.background
import com.phongbaoto.stormbook.utils.banner
import com.phongbaoto.stormbook.utils.banner_2
import com.phongbaoto.stormbook.utils.banner_3
import com.phongbaoto.stormbook.utils.banner_4
import com.phongbaoto.stormbook.utils.hot
import com.phongbaoto.stormbook.utils.propose
import com.phongbaoto.stormbook.viewmodel.authViewModel.LoginViewModel
import kotlinx.coroutines.delay

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var showSuccess by remember { mutableStateOf(true) }
    val isLoginSuccess by viewModel.isLoginSuccess.collectAsState()

    val context = LocalContext.current
    val prefs = remember {
        context.getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
    }

    val isLogin = remember {
        mutableStateOf(prefs.getBoolean("isLoginSuccess", false))
    }

    LaunchedEffect(Unit) {
        Log.d("HomeScreen", "isLoginSuccess từ SharedPreferences: ${isLogin.value}")
        Log.d("HomeScreen", "isLoginSuccess từ ViewModel: $isLoginSuccess")
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val scrollState = rememberScrollState()
    //
    var isShowDialog by remember { mutableStateOf(false) }

    LaunchedEffect(isLogin.value) {
        if (isLogin.value) {
            showSuccess = true
            delay(3000)
            showSuccess = false
            viewModel.clearLoginSuccess()
        }
    }

    HideStatusBar()
    val listCategory = listOf(
        Category(1, "Manhua"),
        Category(2, "Manhwa"),
        Category(3, "Action"),
        Category(4, "Lmao")
    )

    val listStory = listOf(
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
    )

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
                        navController.navigate(ROUTER.Category.name)
                    },
                    isImage = false,
                    image = null
                )

                ListCategory(listCategory = listCategory)

            }

            //de xuat
            Column {
                TitleComponent(
                    title = "Đề xuất",
                    onClick = {},
                    isImage = true,
                    image = propose
                )
                Space(5.dp)
                ListStory(
                    listStory,
                    navController = navController
                )
            }

            //truyen hot
            Space(5.dp)
            Column {
                TitleComponent(
                    title = "Truyện Hot",
                    onClick = {},
                    isImage = true,
                    image = hot
                )
                Space(5.dp)
                ListStory(
                    listStory,
                    navController = navController
                )
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
