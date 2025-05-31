package com.phongbaoto.vnstormbook.navigation

sealed class Screen(val router:String, val title: String, val icon: Int?) {
    object Welcome : Screen(router = "Welcome", title = "Man hinh chao", icon = null)
    object LoginUser: Screen(router = "LoginUser", title = "Login", icon = null)
}