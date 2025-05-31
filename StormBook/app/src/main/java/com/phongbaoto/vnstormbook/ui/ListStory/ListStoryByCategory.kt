package com.phongbaoto.vnstormbook.ui.ListStory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.vnstormbook.data.model.Story
import com.phongbaoto.vnstormbook.ui.theme.Black
import com.phongbaoto.vnstormbook.utils.UtilsComponent.HeaderComponent
import com.phongbaoto.vnstormbook.utils.UtilsComponent.ListWithPagination
import com.phongbaoto.vnstormbook.utils.UtilsComponent.StoryItem
import com.phongbaoto.vnstormbook.utils.banner
import com.phongbaoto.vnstormbook.utils.banner_2
import com.phongbaoto.vnstormbook.utils.banner_3
import com.phongbaoto.vnstormbook.utils.banner_4

@Preview
@Composable
fun PreviewListStoryByCategory(){
    ListStoryByCategory(rememberNavController())
}

@Composable
fun ListStoryByCategory(
    navController: NavController
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val listStory = listOf(
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
    ){

        HeaderComponent(
            title = "Thể loại gì đấy",
            onClick = {},
            navController = navController
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ){
            ListWithPagination(
                list = listStory,
                itemsPerPage = 10,
                onPageChange = {page ->

                },
                paddingBottom = 60.dp,
                itemComponent = {item ->
                    StoryItem(
                        width = screenWidth/2f,
                        height = 200.dp,
                        story = item,
                        onClick = {}
                    )
                }
            )
        }
    }
}