package com.phongbaoto.stormbookv2.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.phongbaoto.stormbookv2.ui.ListStory.ListStoryByCategory
import com.phongbaoto.stormbookv2.ui.adminUI.addChapterStory.AddNewChapter
import com.phongbaoto.stormbookv2.ui.auth.LoginUserScreen
import com.phongbaoto.stormbookv2.ui.category.CategoryScreen
import com.phongbaoto.stormbookv2.ui.adminUI.detailStoryAdmin.DetailStoryAdmin
import com.phongbaoto.stormbookv2.ui.main.HomeScreen
import com.phongbaoto.stormbookv2.ui.adminUI.storyAdmin.AddNewStory
import com.phongbaoto.stormbookv2.ui.auth.ForgotPass
import com.phongbaoto.stormbookv2.ui.auth.PreForgotPass
import com.phongbaoto.stormbookv2.ui.auth.RegisterScreen
import com.phongbaoto.stormbookv2.ui.chapter.ChapterScreen
import com.phongbaoto.stormbookv2.ui.detailStory.DetailStory
import com.phongbaoto.stormbookv2.ui.profile.ProfileScreen
import com.phongbaoto.stormbookv2.ui.welcome.WelcomeScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Box(
        Modifier.fillMaxSize()
    ){
        NavHost(
            navController = navController,
            startDestination = ROUTER.Welcome.name
        ) {
            //
            composable(ROUTER.Welcome.name) {
                WelcomeScreen(navController)
            }
            //
            composable(ROUTER.LoginUser.name) {
                LoginUserScreen(navController)
            }
            //
            composable(ROUTER.Register.name) {
                RegisterScreen(navController)
            }
            //
            composable(ROUTER.Home.name) {
                HomeScreen(navController)
            }
            //
            composable(ROUTER.BottomNav.name) {
                BottomNavigation(navController)
            }
            //
            composable(ROUTER.Category.name) {
                CategoryScreen(navController)
            }
            //
            composable(ROUTER.AddStory.name) {
                AddNewStory(navController)
            }
            //
            composable(ROUTER.DetailStoryAdmin.name) {
                DetailStoryAdmin(navController)
            }
            //
            //cai nay truyen id cua chapter + truyen trang thai hien button
            composable("${ROUTER.DetailStory.name}/{storyId}/{isShowButton}",
                arguments = listOf(
                    navArgument("isShowButton") { type = NavType.BoolType },
                    navArgument("storyId"){type = NavType.LongType}
                )
            ) { navBackStackEntry ->
                val isShowButton = navBackStackEntry.arguments?.getBoolean("isShowButton") ?: false
                val storyId = navBackStackEntry.arguments?.getLong("storyId")
                if (storyId != null) {
                    DetailStory(navController, isShowButton, storyId)
                }
            }
            //

            composable(ROUTER.AddChapter.name) {
                AddNewChapter(navController)
            }

            //profile
            composable(ROUTER.ProfileScreen.name) {
                ProfileScreen(navController)
            }

            //chapter
            composable(ROUTER.ChapterScreen.name) {
                ChapterScreen(navController)
            }

            //list story by category
            composable(ROUTER.ListStoryByCategory.name) {
                ListStoryByCategory(navController)
            }

            //pre forgot pass
            composable(ROUTER.PreForgotPass.name) {
                PreForgotPass(navController)
            }

            composable("${ROUTER.PreForgotPass.name}/{email}/{type}",
                arguments = listOf(
                    navArgument("email") { type = NavType.StringType },
                    navArgument("type") { type = NavType.StringType }
                )
            ) { navBackStackEntry ->
                val email = navBackStackEntry.arguments?.getString("email") ?: ""
                val type = navBackStackEntry.arguments?.getString("type") ?: ""
                PreForgotPass(navController, email, type)
            }
            //forgot pass
            composable("${ROUTER.ForgotPass.name}/{email}",
                arguments = listOf(
                    navArgument(name = "email") { type = NavType.StringType }
                )
            ) { navBackStackEntry ->
                val email = navBackStackEntry.arguments?.getString("email") ?: ""
                ForgotPass(navController, email)
            }
        }
    }
}
