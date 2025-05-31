package com.phongbaoto.vnstormbook.ui.chapter.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phongbaoto.vnstormbook.ui.chapter.data.IconType
import com.phongbaoto.vnstormbook.ui.chapter.data.RenderIcon

@Composable
fun IconChapter(
    icon: IconType,
    onClick: () -> Unit,
    modifier: Modifier
){
    IconButton(
        onClick = {
            onClick()
        },
        modifier = modifier
    ) {
        icon.RenderIcon(
            modifier = Modifier.size(25.dp)
        )
    }
}