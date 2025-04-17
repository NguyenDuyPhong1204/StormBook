package com.phongbaoto.stormbook.ui.storyAdmin

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.data.model.Story
import com.phongbaoto.stormbook.navigation.ROUTER
import com.phongbaoto.stormbook.ui.storyAdmin.component.PendingStory
import com.phongbaoto.stormbook.ui.storyAdmin.component.PostedStory
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.BlueButton
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.UtilsComponent.ButtonComponent
import com.phongbaoto.stormbook.utils.UtilsComponent.CustomTabLayout
import com.phongbaoto.stormbook.utils.UtilsComponent.HeaderComponent
import com.phongbaoto.stormbook.utils.UtilsComponent.Space
import com.phongbaoto.stormbook.utils.banner
import com.phongbaoto.stormbook.utils.banner_2
import com.phongbaoto.stormbook.utils.banner_3
import com.phongbaoto.stormbook.utils.banner_4

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun StoryScreen(
    navController: NavController
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val tabs = listOf("Truyện đã đăng tải", "Truyện đang chờ duyệt")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .statusBarsPadding()
    ) {

        // Column chính chứa tab và nội dung
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Tạo không gian cho button ở dưới
        ) {
            Space(10.dp)
            CustomTabLayout(
                tabs = tabs,
                modifier = Modifier.weight(1f) // Cho phép nội dung tab chiếm hết không gian còn lại
            ) { selectedTabIndex ->
                when (selectedTabIndex) {
                    0 -> PostedStory()
                    1 -> PendingStory()
                }
            }
        }

        // Button được đặt cố định ở cuối màn hình
        ButtonComponent(
            title = "Thêm truyện mới",
            textColor = White,
            fontWeight = FontWeight.SemiBold,
            color = BlueButton,
            image = null,
            onClick = {
                navController.navigate(ROUTER.AddStory.name)
            },
            modifier =  Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 15.dp, end = 15.dp, bottom = 110.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStory(){
    val navController = rememberNavController()
    StoryScreen(
        navController
    )
}