package com.phongbaoto.stormbookv2.ui.welcome

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.phongbaoto.stormbookv2.R
import com.phongbaoto.stormbookv2.navigation.ROUTER
import com.phongbaoto.stormbookv2.receiver.NetworkReceiver
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.utils.FunUtils
import com.phongbaoto.stormbookv2.utils.UtilsComponent.dialog.NotifyDialogComponent
import com.phongbaoto.stormbookv2.utils.notifyInternet
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen(navController: NavController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcome_animation))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever // Loop liên tục
    )
    val context = LocalContext.current
    var hasCheckedNetwork by remember { mutableStateOf(false) }
    var showNetworkError by remember { mutableStateOf(false) }
    var alreadyNavigated by remember { mutableStateOf(false) }
    var shouldRetry by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(3500)
        if (FunUtils.isNetworkAvailable(context)) {
            if (!alreadyNavigated) {
                alreadyNavigated = true
                navController.navigate(ROUTER.LoginUser.name) {
                    popUpTo(ROUTER.Welcome.name) { inclusive = true }
                }
            }
        } else {
            showNetworkError = true
        }
        hasCheckedNetwork = true
    }
// Effect để kiểm tra lại kết nối mạng liên tục sau khi bấm OK
    LaunchedEffect(shouldRetry) {
        if (shouldRetry) {
            while (true) {
                delay(3000) // mỗi 3 giây kiểm tra lại
                if (FunUtils.isNetworkAvailable(context)) {
                    if (!alreadyNavigated) {
                        alreadyNavigated = true
                        navController.navigate(ROUTER.LoginUser.name) {
                            popUpTo(ROUTER.Welcome.name) { inclusive = true }
                        }
                        break
                    }
                } else {
                    showNetworkError = true
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )

        if (showNetworkError) {
            NotifyDialogComponent(
                showDialog = showNetworkError,
                onClick = {
                    showNetworkError = false
                    shouldRetry = true
                },
                contentNotify = notifyInternet,
                textButton = "Thử lại"
            )
        }

    }
}
