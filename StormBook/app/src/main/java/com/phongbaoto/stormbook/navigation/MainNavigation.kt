package com.phongbaoto.stormbook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.phongbaoto.stormbook.ui.adminUI.addChapterStory.AddNewChapter
import com.phongbaoto.stormbook.ui.auth.LoginUserScreen
import com.phongbaoto.stormbook.ui.category.CategoryScreen
import com.phongbaoto.stormbook.ui.adminUI.detailStoryAdmin.DetailStoryAdmin
import com.phongbaoto.stormbook.ui.main.HomeScreen
import com.phongbaoto.stormbook.ui.adminUI.storyAdmin.AddNewStory
import com.phongbaoto.stormbook.ui.chapter.ChapterScreen
import com.phongbaoto.stormbook.ui.detailStory.DetailStory
import com.phongbaoto.stormbook.ui.profile.ProfileScreen
import com.phongbaoto.stormbook.ui.welcome.WelcomeScreen

@Composable
fun MainNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ROUTER.BottomNav.name
    ) {
        //
        composable(ROUTER.Welcome.name){
            WelcomeScreen(navController)
        }
        //
        composable(ROUTER.LoginUser.name){
            LoginUserScreen(navController)
        }
        //
        composable(ROUTER.Home.name){
            HomeScreen(navController)
        }
        //
        composable(ROUTER.BottomNav.name){
            BottomNavigation(navController)
        }
        //
        composable(ROUTER.Category.name){
            CategoryScreen(navController)
        }
        //
        composable(ROUTER.AddStory.name){
            AddNewStory(navController)
        }
        //
        composable(ROUTER.DetailStoryAdmin.name){
            DetailStoryAdmin(navController)
        }
        //
        //cai nay truyen id cua chapter + truyen trang thai hien button
        composable("${ROUTER.DetailStory.name}/{isShowButton}",
                arguments = listOf(
                    navArgument("isShowButton"){type = NavType.BoolType}
                )
            ){ navBackStackEntry ->
            val isShowButton = navBackStackEntry.arguments?.getBoolean("isShowButton") ?: false
            DetailStory(navController, isShowButton)
        }
        //

        composable(ROUTER.AddChapter.name){
            AddNewChapter(navController)
        }

        //profile
        composable(ROUTER.ProfileScreen.name){
            ProfileScreen(navController)
        }

        //chapter
        composable(ROUTER.ChapterScreen.name){
           ChapterScreen(navController)
        }

    }
}
