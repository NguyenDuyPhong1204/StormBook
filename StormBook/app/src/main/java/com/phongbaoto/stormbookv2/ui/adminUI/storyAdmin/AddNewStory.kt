package com.phongbaoto.stormbookv2.ui.adminUI.storyAdmin

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbookv2.data.model.Category
import com.phongbaoto.stormbookv2.ui.adminUI.storyAdmin.component.DialogSelectCategory
import com.phongbaoto.stormbookv2.ui.adminUI.storyAdmin.component.SelectCategoryField
import com.phongbaoto.stormbookv2.ui.adminUI.storyAdmin.component.SelectImage
import com.phongbaoto.stormbookv2.ui.adminUI.storyAdmin.component.TextFieldStory
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.BlueButton
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.UtilsComponent.ButtonComponent
import com.phongbaoto.stormbookv2.utils.UtilsComponent.HeaderComponent
import com.phongbaoto.stormbookv2.utils.UtilsComponent.Space

@Composable
fun AddNewStory(
    navController: NavController
) {
    //ten truyen
    var nameStory by remember { mutableStateOf("") }
    var isFocusedName by remember { mutableStateOf(false) }
    //trang thai
    var statusStory by remember { mutableStateOf("") }
    var isFocusedStatus by remember { mutableStateOf(false) }
    //chon the loai truyen
    var valueCategory by remember { mutableStateOf("") }
    var selectCategory by remember { mutableStateOf(listOf<Category>()) }
    var showDialog by remember { mutableStateOf(false) }
    //gioi thieu
    var introduceStory by remember { mutableStateOf("") }
    var isFocusedIntroduce by remember { mutableStateOf(false) }
    //anh
    var selectedImageUrl by remember { mutableStateOf<Uri?>(null) }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(bottom = 50.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // Bỏ hiệu ứng khi click
            ) {
                // Khi click vào (bất kỳ đâu trên màn hình), hủy tất cả focus
                focusManager.clearFocus()
            }
            .verticalScroll(scrollState)
    ) {
        HeaderComponent(
            title = "Thêm truyện mới",
            navController = navController,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            //ten truyen
            TextFieldStory(
                title = "Tên truyện",
                value = nameStory,
                onValueChange = { name ->
                    nameStory = name
                },
                isFocused = remember { mutableStateOf(isFocusedName) },
                placeholder = "Nhập tên truyện",
                isRequired = true
            )

            //trang thai
            TextFieldStory(
                title = "Trạng thái",
                value = statusStory,
                onValueChange = {status ->
                    statusStory = status
                },
                isFocused = remember { mutableStateOf(isFocusedStatus) },
                placeholder = "VD: Đang cập nhật,...",
                isRequired = true
            )

            //chon the loai
            SelectCategoryField(
                title = "Thể loại truyện",
                value = valueCategory,
                onValueChange = {category ->
                    valueCategory = category
                },
                onShowDialog = {
                    showDialog = true
                },
                placeholder = "Chọn thể loại truyện",
            )

            //gioi thieu
            TextFieldStory(
                title = "Giới thiệu",
                value = introduceStory,
                onValueChange = {introduce ->
                    introduceStory = introduce
                },
                isFocused = remember { mutableStateOf(isFocusedIntroduce)},
                placeholder = "Thông tin giới thiệu về truyện",
                isRequired = false
            )

            //chon anh
            Space(5.dp)
            SelectImage(
                initialImage = selectedImageUrl,
                onImageSelected = { uri ->
                    selectedImageUrl = uri
                    // Bạn có thể lưu uri này vào ViewModel hoặc chuyển nó đến màn hình khác
                    Log.d("SelectImage", "Selected image URI: $uri")
                }
            )

            //button
            Space(10.dp)
            ButtonComponent(
                title = "Đăng tải",
                color = BlueButton,
                textColor = White,
                fontWeight = FontWeight.Bold,
                isImage = false,
                modifier = Modifier.fillMaxWidth(),
                image = null,
                onClick = {}
            )

        }
        if(showDialog){
            DialogSelectCategory(
                showDialog = showDialog,
                onDismiss = {
                    showDialog = false
                },
                onConfirm = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddStory() {
    val navController = rememberNavController()
    AddNewStory(navController)
}