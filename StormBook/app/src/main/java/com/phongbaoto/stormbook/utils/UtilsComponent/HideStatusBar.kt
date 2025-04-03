package com.phongbaoto.stormbook.utils.UtilsComponent

import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HideStatusBarAndNavigation() {
    val systemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false  // Ẩn thanh StatusBar
    systemUiController.isNavigationBarVisible = false //an thanh navigation
}
