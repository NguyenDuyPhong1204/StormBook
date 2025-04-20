package com.phongbaoto.stormbook.ui.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.phongbaoto.stormbook.data.model.Story
import com.phongbaoto.stormbook.utils.UtilsComponent.StoryItem

@Composable
fun ListStory(
    listStory: List<Story>
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(listStory){item ->
            StoryItem(
                width = 165.dp,
                height = 200.dp,
                story = item,
                onClick = {}
            )
        }
    }
}