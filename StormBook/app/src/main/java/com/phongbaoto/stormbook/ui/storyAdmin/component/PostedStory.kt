package com.phongbaoto.stormbook.ui.storyAdmin.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.phongbaoto.stormbook.data.model.Story
import com.phongbaoto.stormbook.utils.banner
import com.phongbaoto.stormbook.utils.banner_2
import com.phongbaoto.stormbook.utils.banner_3
import com.phongbaoto.stormbook.utils.banner_4

@Composable
fun PostedStory(
    navController: NavController
) {
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
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
    ) {
        StoryList(listStory, onClickItem = {})
    }
}