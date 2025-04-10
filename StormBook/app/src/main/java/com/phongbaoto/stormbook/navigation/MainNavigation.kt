package com.phongbaoto.stormbook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.ui.auth.LoginUserScreen
import com.phongbaoto.stormbook.ui.category.CategoryScreen
import com.phongbaoto.stormbook.ui.main.HomeScreen
import com.phongbaoto.stormbook.ui.welcome.WelcomeScreen

@Composable
fun MainNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ROUTER.BottomNav.name
    ) {
        composable(ROUTER.Welcome.name){
            WelcomeScreen(navController)
        }
        composable(ROUTER.LoginUser.name){
            LoginUserScreen(navController)
        }
        composable(ROUTER.Home.name){
            HomeScreen(navController)
        }
        composable(ROUTER.BottomNav.name){
            BottomNavigation(navController)
        }
        composable(ROUTER.Category.name){
            CategoryScreen(navController)
        }
    }
}
