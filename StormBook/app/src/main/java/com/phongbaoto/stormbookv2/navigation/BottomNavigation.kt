package com.phongbaoto.stormbookv2.navigation

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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbookv2.ui.category.CategoryScreen
import com.phongbaoto.stormbookv2.ui.main.HomeScreen
import com.phongbaoto.stormbookv2.ui.adminUI.storyAdmin.StoryScreen
import com.phongbaoto.stormbookv2.ui.profile.ProfileScreen
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.RedIndicator
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.category
import com.phongbaoto.stormbookv2.utils.home
import com.phongbaoto.stormbookv2.utils.person
import com.phongbaoto.stormbookv2.utils.story

@Composable
fun BottomNavigation(
    navController: NavController
) {
    val navItemList = listOf(
        NavItem("Trang chủ", home),
        NavItem("Thể loại", category),
        NavItem("Truyện", story),
        NavItem("Cá nhân", person)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
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
                            indicatorColor = Color.Transparent // Quan trọng: đặt màu nền indicator thành trong suốt
                        )
                    )
                }

            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selectedIndex,
            navController,
            onTabChange = {
                selectedIndex = it
            }
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navController: NavController,
    onTabChange: (Int) -> Unit = {}
) {

    when (selectedIndex) {
        0 -> HomeScreen(
            navController = navController,
            onNavigateToCategory = {
                onTabChange(1) // Chuyển sang tab "Thể loại" (index = 1)
            }
        )        1 -> CategoryScreen(navController)
        2 -> StoryScreen(navController)
        3 -> ProfileScreen(navController)
    }
}

