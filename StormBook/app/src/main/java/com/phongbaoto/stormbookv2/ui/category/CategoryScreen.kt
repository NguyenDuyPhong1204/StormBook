package com.phongbaoto.stormbookv2.ui.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbookv2.navigation.ROUTER
import com.phongbaoto.stormbookv2.ui.category.component.DialogAddCategory
import com.phongbaoto.stormbookv2.ui.category.component.ListCategory
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.utils.UtilsComponent.HeaderComponent
import com.phongbaoto.stormbookv2.utils.plus
import com.phongbaoto.stormbookv2.viewmodel.authViewModel.LoginViewModel
import com.phongbaoto.stormbookv2.viewmodel.categoryViewModel.CategoryUiState
import com.phongbaoto.stormbookv2.viewmodel.categoryViewModel.CategoryViewModel

@Composable
fun CategoryScreen(
    navController: NavController,
    viewModel: CategoryViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }

    //viewmodel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    //lay thong tin nguoi dung
    val userInfo = loginViewModel.getUserInfo()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
//           .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
            .statusBarsPadding()
    ) {

//       HideStatusBar()

        userInfo?.role?.let {
            HeaderComponent(
                isBack = false,
                title = "Danh sách thể loại",
                onClick = {
                    showDialog = true
                },
                role = it,
                navController = navController,
                rightIcon = plus,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp)
        ) {
//            ListCategory(
//                listCategory = listCategory
//            )
            when (uiState) {
                CategoryUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is CategoryUiState.Error -> {
                    Text((uiState as CategoryUiState.Error).message)
                }

                is CategoryUiState.Success -> {
                    ListCategory((uiState as CategoryUiState.Success).data)
                }
            }

            if (showDialog) {
                DialogAddCategory(
                    showDialog = showDialog,
                    onDismiss = {
                        showDialog = false
                    },
                    onConfirm = {}
                )
            }

        }
    }
}
