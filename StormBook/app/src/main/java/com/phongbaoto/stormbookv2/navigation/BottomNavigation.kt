package com.phongbaoto.stormbookv2.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.phongbaoto.stormbookv2.data.model.auth.AuthResponse
import com.phongbaoto.stormbookv2.data.model.auth.Role
import com.phongbaoto.stormbookv2.ui.ListStory.StoryUserScreen
import com.phongbaoto.stormbookv2.ui.category.CategoryScreen
import com.phongbaoto.stormbookv2.ui.main.HomeScreen
import com.phongbaoto.stormbookv2.ui.adminUI.storyAdmin.StoryAdminScreen
import com.phongbaoto.stormbookv2.ui.profile.ProfileScreen
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.RedIndicator
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.category
import com.phongbaoto.stormbookv2.utils.home
import com.phongbaoto.stormbookv2.utils.person
import com.phongbaoto.stormbookv2.utils.story
import com.phongbaoto.stormbookv2.viewmodel.authViewModel.LoginViewModel

@Composable
fun BottomNavigation(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val userInfo = viewModel.getUserInfo()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Kiểm tra nếu không phải màn Detail thì mới hiển thị BottomBar
    val showBottomBar = currentRoute?.startsWith("DetailStory") != true

    // Ghi nhớ selectedIndex, nhưng nhớ khi quay lại
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    val navItemList = listOf(
        NavItem("Trang chủ", home),
        NavItem("Thể loại", category),
        NavItem("Truyện", story),
        NavItem("Cá nhân", person)
    )

    LaunchedEffect(navBackStackEntry) {
        val previousTab = navBackStackEntry
            ?.savedStateHandle
            ?.get<Int>("previousTab")

        if (previousTab != null) {
            selectedIndex = previousTab
            navBackStackEntry?.savedStateHandle?.remove<Int>("previousTab")
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Black,
                    contentColor = Black
                ) {
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(navItem.icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = if (selectedIndex == index) RedIndicator else White
                                )
                            },
                            label = {
                                Text(
                                    text = navItem.label,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (selectedIndex == index) RedIndicator else White
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = RedIndicator,
                                unselectedIconColor = White,
                                selectedTextColor = White,
                                unselectedTextColor = White,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        if (userInfo != null) {
            ContentScreen(
                modifier = Modifier.padding(innerPadding),
                selectedIndex = selectedIndex,
                onTabChange = { selectedIndex = it },
                navController = navController,
                user = userInfo
            )
        }
    }
}


@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavController,
    onTabChange: (Int) -> Unit = {},
    user: AuthResponse
) {
    when (selectedIndex) {
        0 -> HomeScreen(
            navController = navController,
            onNavigateToCategory = { onTabChange(1) },
            onNavigateToStory = { onTabChange(2) },
            onNavigateToDetail = {
//                navController.currentBackStackEntry?.savedStateHandle?.set("previousTab", selectedIndex)
//                navController.navigate("${ROUTER.DetailStory.name}/{storyId}/false"){
//                    launchSingleTop = true
//                }
            }
        )

        1 -> CategoryScreen(navController)

        2 -> {
            if (user.role == Role.USER) {
                StoryUserScreen(
                    navController = navController,
                    onNavigateToDetail = {
//                        navController.currentBackStackEntry?.savedStateHandle?.set("previousTab", selectedIndex)
//                        navController.navigate("${ROUTER.DetailStory.name}/{storyId}/false"){
//                            launchSingleTop = true
//                        }
                    }
                )
            } else {
                StoryAdminScreen(navController)
            }
        }

        3 -> ProfileScreen(navController)
    }
}


