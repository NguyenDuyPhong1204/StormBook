package com.phongbaoto.stormbook.ui.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.phongbaoto.stormbook.R
import com.phongbaoto.stormbook.ROUTER
import com.phongbaoto.stormbook.ui.theme.Black
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navController: NavController){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcome_animation))
    val progress by animateLottieCompositionAsState(composition)

    LaunchedEffect(Unit) {
        delay(3500)
        navController.navigate(ROUTER.LoginUser.name)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
    ){
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}
