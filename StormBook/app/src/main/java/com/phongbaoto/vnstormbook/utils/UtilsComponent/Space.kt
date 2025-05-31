package com.phongbaoto.vnstormbook.utils.UtilsComponent

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Space(
    padding: Dp
){
    Spacer(
        modifier = Modifier.padding(padding)
    )
}