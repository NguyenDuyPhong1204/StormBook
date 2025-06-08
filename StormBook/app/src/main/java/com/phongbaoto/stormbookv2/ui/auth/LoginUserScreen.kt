package com.phongbaoto.stormbookv2.ui.auth

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.phongbaoto.stormbookv2.BuildConfig
import com.phongbaoto.stormbookv2.navigation.ROUTER
import com.phongbaoto.stormbookv2.ui.auth.component.GoToRegister
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.BlueButton
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.UtilsComponent.button.ButtonComponent
import com.phongbaoto.stormbookv2.utils.UtilsComponent.DividerWithText
import com.phongbaoto.stormbookv2.utils.UtilsComponent.Space
import com.phongbaoto.stormbookv2.utils.UtilsComponent.textField.TextFieldComponent
import com.phongbaoto.stormbookv2.ui.auth.component.WelcomeComponent
import com.phongbaoto.stormbookv2.utils.UtilsComponent.LoadingComponent
import com.phongbaoto.stormbookv2.utils.UtilsComponent.dialog.NotifyDialogComponent
import com.phongbaoto.stormbookv2.utils.google
import com.phongbaoto.stormbookv2.utils.login
import com.phongbaoto.stormbookv2.utils.notifyInternet
import com.phongbaoto.stormbookv2.viewmodel.authViewModel.LoginViewModel
import com.phongbaoto.stormbookv2.viewmodel.networkViewModel.NetworkViewModel

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun LoginUserScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    networkViewModel: NetworkViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val webClientAPI = BuildConfig.WEB_CLIENT_ID
    //thong tin dang nhap
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isFocusedEmail by remember { mutableStateOf(false) }
    val isFocusedPass by remember { mutableStateOf(false) }
    //
    val focusManager = LocalFocusManager.current
    //
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(webClientAPI)
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    // Google Sign-In result launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account.idToken
            if (idToken != null) {
                viewModel.loginWithGoogle(idToken)
            } else {
                Toast.makeText(context, "Không lấy được ID token", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Lỗi không xác định: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    //thuoc tinh hien thi dialog
    var showDialog by remember { mutableStateOf(false) }

    //lay thuoc tinh tu viewmodel
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val loginResult by viewModel.loginResult.collectAsState()
    LaunchedEffect(loginResult) {
        loginResult?.onSuccess {
            navController.navigate(ROUTER.BottomNav.name) {
                popUpTo(ROUTER.LoginUser.name) { inclusive = true }
            }
        }
    }

    LaunchedEffect(errorMessage) {
        if (errorMessage != null) {
            showDialog = true
        }
    }

    val isConnected by networkViewModel.isConnected.collectAsState()
    var showNetworkError by remember { mutableStateOf(false) }
    var alreadyNavigated by remember { mutableStateOf(false) }
    // Nếu mất kết nối mạng thì điều hướng về WelcomeScreen
    LaunchedEffect(isConnected) {
        if (!isConnected) {
            showNetworkError = true
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(top = 50.dp, start = 15.dp, end = 15.dp)
            .navigationBarsPadding()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // Bỏ hiệu ứng khi click
            ) {
                // Khi click vào (bất kỳ đâu trên màn hình), hủy tất cả focus
                focusManager.clearFocus()
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            WelcomeComponent(title = login)
            //email
            TextFieldComponent(
                value = email,
                onValueChange = { valueEmail ->
                    email = valueEmail
                },
                placeholder = "Vui lòng nhập email!",
                isFocused = remember { mutableStateOf(isFocusedEmail) }
            )
            //password
            Space(7.dp)
            TextFieldComponent(
                value = password,
                onValueChange = { valuePassword ->
                    password = valuePassword
                },
                placeholder = "Vui lòng nhập mật khẩu!",
                isFocused = remember { mutableStateOf(isFocusedPass) },
                isPassword = true
            )

            //quen mat khau
            Space(5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Quên mật khẩu ?",
                    fontSize = 16.sp,
                    color = White,
                    textAlign = TextAlign.End
                )
            }

            //button dang nhap
            Space(20.dp)
            ButtonComponent(
                title = "Đăng nhập",
                onClick = {
                    viewModel.login(email, password)
                },
                color = BlueButton,
                textColor = White,
                image = null,
                isImage = false,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            //hoac
            Space(15.dp)
            DividerWithText("Hoặc")

            Space(15.dp)
            ButtonComponent(
                title = "Đăng nhập bằng Google",
                onClick = {
                    val signInIntent = googleSignInClient.signInIntent
                    launcher.launch(signInIntent)
                },
                color = White,
                textColor = Black,
                image = google,
                isImage = true,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            //khong co tai khoan
            Space(20.dp)
            GoToRegister(navController = navController)
            if (showDialog && errorMessage != null) {
                NotifyDialogComponent(
                    showDialog = showDialog,
                    onClick = {
                        showDialog = false
                    },
                    contentNotify = errorMessage!!,
                    textButton = "Thử lại"
                )
            }

        }
    }
    if (isLoading) {
        LoadingComponent(isLoading)
    }

    if (showNetworkError) {
        NotifyDialogComponent(
            showDialog = showNetworkError,
            onClick = {
                showNetworkError = false
                navController.navigate(ROUTER.Welcome.name) {
                    popUpTo(ROUTER.LoginUser.name) { inclusive = true }
                }
            },
            contentNotify = notifyInternet,
            textButton = "Thử lại"
        )
    }

}
