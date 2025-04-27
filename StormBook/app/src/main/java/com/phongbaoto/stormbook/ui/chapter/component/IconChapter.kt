package com.phongbaoto.stormbook.ui.chapter.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.phongbaoto.stormbook.ui.chapter.data.IconType
import com.phongbaoto.stormbook.ui.chapter.data.RenderIcon
import com.phongbaoto.stormbook.ui.theme.White

@Composable
fun IconChapter(
    icon: IconType,
    onClick: () -> Unit,
    modifier: Modifier
){
    IconButton(
        onClick = {},
        modifier = modifier
    ) {
        icon.RenderIcon(
            modifier = Modifier.size(25.dp)
        )
    }
}