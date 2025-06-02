package com.phongbaoto.stormbookv2.ui.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.phongbaoto.stormbookv2.data.model.Story
import com.phongbaoto.stormbookv2.navigation.ROUTER
import com.phongbaoto.stormbookv2.utils.UtilsComponent.StoryItem

@Composable
fun ListStory(
    listStory: List<Story>,
    navController: NavController
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(listStory){item ->
            StoryItem(
                width = 165.dp,
                height = 230.dp,
                story = item,
                onClick = {
                    navController.navigate("${ROUTER.DetailStory.name}/${false}")
                }
            )
        }
    }
}