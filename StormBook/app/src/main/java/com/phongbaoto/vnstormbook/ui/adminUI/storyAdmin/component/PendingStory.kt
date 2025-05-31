package com.phongbaoto.vnstormbook.ui.adminUI.storyAdmin.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.phongbaoto.vnstormbook.data.model.Story
import com.phongbaoto.vnstormbook.navigation.ROUTER
import com.phongbaoto.vnstormbook.utils.banner
import com.phongbaoto.vnstormbook.utils.banner_2
import com.phongbaoto.vnstormbook.utils.banner_3

@Composable
fun PendingStory(
    navController: NavController
){
    val listStory = listOf(
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(3, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(3, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(3, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(3, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(3, banner_3, "Ta Là Tà Đế", 100),

    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
    ){
        StoryList(
            listStory,
            onClickItem = {
                navController.navigate(ROUTER.DetailStoryAdmin.name)
            }
        )
    }
}