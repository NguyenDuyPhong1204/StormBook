package com.phongbaoto.vnstormbook.ui.chapter.data

import androidx.compose.ui.graphics.vector.ImageVector

sealed class IconType {
    data class Vector(val icon: ImageVector) : IconType()
    data class Resource(val resId: Int) : IconType()
}