package com.phongbaoto.stormbookv2.utils.UtilsComponent

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.BlueButton
import com.phongbaoto.stormbookv2.ui.theme.BlueTab
import com.phongbaoto.stormbookv2.ui.theme.White

@Composable
fun CustomTabLayout(
    tabs: List<String>,
    modifier: Modifier,
    initialTabIndex: Int = 0,
    tabContent: @Composable (selectedTabIndex: Int) -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(initialTabIndex) }
    var previousIndex by remember { mutableStateOf(initialTabIndex) }
    val density = LocalDensity.current

    // Lưu vị trí và kích thước của các tab
    val tabPositions = remember { List(tabs.size) { mutableStateOf(0f) } }
    val tabWidths = remember { List(tabs.size) { mutableStateOf(0f) } }

    // Animation cho indicator
    val indicatorOffset by animateFloatAsState(
        targetValue = tabPositions[selectedTabIndex].value,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "indicatorOffset"
    )

    val indicatorWidth by animateFloatAsState(
        targetValue = tabWidths[selectedTabIndex].value,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "indicatorWidth"
    )

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(Black)
        ) {
            // Tab items
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                tabs.forEachIndexed { index, title ->
                    val isSelected = index == selectedTabIndex

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable {
                                previousIndex = selectedTabIndex
                                selectedTabIndex = index
                            }
                            .onGloballyPositioned { coordinates ->
                                // Lưu vị trí và kích thước của tab
                                tabPositions[index].value = coordinates.positionInParent().x
                                tabWidths[index].value = coordinates.size.width.toFloat()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = if (isSelected) BlueTab else White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
                        )
                    }
                }
            }

            // Animated indicator
            Box(
                modifier = Modifier
                    .offset(x = with(density) { indicatorOffset.toDp() })
                    .width(with(density) { indicatorWidth.toDp() })
                    .align(Alignment.BottomStart)
            ) {
                Divider(
                    color = BlueButton,
                    thickness = 2.dp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Animated content
        AnimatedContent(
            targetState = selectedTabIndex,
            transitionSpec = {
                if (targetState > initialState) {
                    // Chuyển từ trái sang phải
                    slideInHorizontally { width -> width } + fadeIn() togetherWith
                            slideOutHorizontally { width -> -width } + fadeOut()
                } else {
                    // Chuyển từ phải sang trái
                    slideInHorizontally { width -> -width } + fadeIn() togetherWith
                            slideOutHorizontally { width -> width } + fadeOut()
                }.using(
                    // Thời gian chuyển đổi
                    sizeTransform = null
                )
            },
            label = "tabContentAnimation"
        ) { index ->
            tabContent(index)
        }
    }

}