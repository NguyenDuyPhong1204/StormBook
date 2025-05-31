package com.phongbaoto.stormbookv2.utils.UtilsComponent

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun <T> ListWithPagination(
    list: List<T>,
    itemsPerPage: Int = 10, //so luong item tren moi trang
    onPageChange: (Int) -> Unit = {},
    paddingBottom: Dp,
    itemComponent: @Composable (item: T) -> Unit
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val minItemWidth = screenWidth / 3.5f
    //state de quan ly trang hien tai
    var currentPage by remember { mutableStateOf(1) }
    //tinh tong so trang dua tren danh sach va so luong item moi trang
    val totalPages = (list.size + itemsPerPage - 1) / itemsPerPage
    //phan trang danh sach
    val paginatedList = list
        .chunked(itemsPerPage)
        .getOrElse(currentPage - 1){ emptyList() }
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minItemWidth),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = screenHeight/7f)
        ) {
            items(list) { item ->
                itemComponent(item)
            }
        }
        // Component phân trang ở dưới cùng
        PaginationControls(
            currentPage = currentPage,
            totalPage = totalPages,
            onPageSelected = { page ->
                currentPage = page
                onPageChange(page)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = paddingBottom)
        )

    }
}


