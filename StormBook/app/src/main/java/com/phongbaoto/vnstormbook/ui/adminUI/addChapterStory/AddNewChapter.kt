package com.phongbaoto.vnstormbook.ui.adminUI.addChapterStory

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.vnstormbook.ui.adminUI.addChapterStory.component.SelectMultipleImages
import com.phongbaoto.vnstormbook.ui.adminUI.addChapterStory.component.TextFieldChapter
import com.phongbaoto.vnstormbook.ui.theme.Black
import com.phongbaoto.vnstormbook.utils.UtilsComponent.HeaderComponent
import com.phongbaoto.vnstormbook.utils.UtilsComponent.Space

@Preview(showBackground = true)
@Composable
fun PreviewAddChapter(){
    AddNewChapter(rememberNavController())
}

@Composable
fun AddNewChapter(
    navController: NavController,
){
    //chuong truyen
    var chapterNumber by remember { mutableStateOf("") }
    var isFocusedNumber by remember { mutableStateOf(false) }
    //tieu de chuong
    var chapterTitle by remember { mutableStateOf("") }
    var isFocusTitle by remember { mutableStateOf(false) }
    //anh chuong
    var chapterImages by remember { mutableStateOf(listOf<Uri>()) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
    ){
        HeaderComponent(
            title = "Thêm chương truyện",
            navController = navController,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ){
            //chapter number
            TextFieldChapter(
                title = "Chương",
                value = chapterNumber,
                onValueChange = { number ->
                    chapterNumber = number
                },
                isRequired = true,
                placeholder = "VD: 123",
                isFocused = remember { mutableStateOf(isFocusedNumber) }
            )

            //chapter title
            TextFieldChapter(
                title = "Tiêu đề",
                value = chapterTitle,
                onValueChange = { title ->
                    chapterTitle = title
                },
                isRequired = false,
                placeholder = "VD: Fixed",
                isFocused = remember { mutableStateOf(isFocusTitle) }
            )
            //chon anh
            Space(15.dp)
            SelectMultipleImages(
                initialImages = listOf(), //danh sach uri neu chinh sua anh
                onImagesSelected = {uris ->
                    //xu ly danh sach uri
                }
            )
        }

    }
}