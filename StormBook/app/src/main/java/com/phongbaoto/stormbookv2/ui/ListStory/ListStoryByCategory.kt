package com.phongbaoto.stormbookv2.ui.ListStory

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
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.utils.UtilsComponent.HeaderComponent

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
//            ListWithPagination(
//                list = listStory,
//                itemsPerPage = 10,
//                onPageChange = {page ->
//
//                },
//                paddingBottom = 60.dp,
//                itemComponent = {item ->
//                    StoryItem(
//                        width = screenWidth/2f,
//                        height = 200.dp,
//                        story = item,
//                        onClick = {}
//                    )
//                }
//            )
        }
    }
}