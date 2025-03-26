package com.phongbaoto.stormbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.navigation.Screen
import com.phongbaoto.stormbook.ui.theme.StormBookTheme
import com.phongbaoto.stormbook.ui.welcome.WelcomeScreen
import com.phongbaoto.stormbook.ui.auth.LoginUserScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainNavigation()
        }
    }
}

@Composable
fun MainNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ROUTER.Welcome.name
    ) {
        composable(ROUTER.Welcome.name){
            WelcomeScreen(navController)
        }
        composable(ROUTER.LoginUser.name){
            LoginUserScreen(navController)
        }
    }
}

enum class ROUTER{
    Welcome,
    LoginUser
}