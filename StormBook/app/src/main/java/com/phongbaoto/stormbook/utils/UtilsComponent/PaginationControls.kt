package com.phongbaoto.stormbook.utils.UtilsComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.RedIn
import com.phongbaoto.stormbook.ui.theme.White

@Composable
fun PaginationControls(
    currentPage: Int,
    totalPage: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    if(totalPage <=1) return //khong hien thi phan trang neu chi co 1 trang
    val visiblePageCount = 5 //so luong trang hien thi
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(Black)
            .padding(vertical = 8.dp)
    ){

        //nut pre
        IconButton(
            onClick = {
                if(currentPage > 1){
                    onPageSelected(currentPage -1)
                }
            },
            enabled = currentPage >1,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = White,
                    shape = CircleShape
                )
                .size(40.dp),
//            colors = IconButtonColors(
//                contentColor = if(cu)
//            )
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "Previous Page",
                tint = if(currentPage > 1) White else Color.Gray
            )
        }
        Space(8.dp)
        //tinh toan cac trang se hien thi
        // Tính toán start và end page để hiển thị
        val half = visiblePageCount / 2
        val startPage = when {
            currentPage <= half -> 1
            currentPage >= totalPage - half -> totalPage - visiblePageCount + 1
            else -> currentPage - half
        }.coerceAtLeast(1)

        val endPage = (startPage + visiblePageCount - 1).coerceAtMost(totalPage)

        for (page in startPage..endPage) {
            PageNumber(
                page = page,
                isSelected = page == currentPage,
                onClick = { onPageSelected(page) }
            )
        }

        //nut next
        Space(8.dp)
        IconButton(
            onClick = {
                if (currentPage < totalPage) {
                    onPageSelected(currentPage + 1)
                }
            },
            enabled = currentPage < totalPage,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = White,
                    shape = CircleShape
                )
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Next Page",
                tint = if (currentPage < totalPage) White else Color.Gray
            )
        }

    }
}

@Composable
fun PageNumber(
    page: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.padding(horizontal = 6.dp)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(if (isSelected) RedIn else Color.Transparent)
                .border(
                    border = BorderStroke(width = 1.dp, color = White),
                    shape = CircleShape
                )
                .clickable(onClick = onClick)
        ) {
            Text(
                text = page.toString(),
                color = White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

// Thêm extension function cho giúp phân trang dễ hơn
fun <T> List<T>.paginate(pageSize: Int, page: Int): List<T> {
    val startIndex = (page - 1) * pageSize
    val endIndex = minOf(startIndex + pageSize, this.size)
    return if (startIndex < this.size) this.subList(startIndex, endIndex) else emptyList()
}