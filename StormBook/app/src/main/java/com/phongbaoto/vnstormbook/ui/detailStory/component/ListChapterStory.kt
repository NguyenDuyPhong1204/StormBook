package com.phongbaoto.vnstormbook.ui.detailStory.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.phongbaoto.vnstormbook.data.model.Chapter
import com.phongbaoto.vnstormbook.navigation.ROUTER
import com.phongbaoto.vnstormbook.ui.adminUI.detailStoryAdmin.component.RowContent
import com.phongbaoto.vnstormbook.ui.theme.Black
import com.phongbaoto.vnstormbook.ui.theme.BlueButton
import com.phongbaoto.vnstormbook.ui.theme.White
import com.phongbaoto.vnstormbook.utils.FunUtils
import com.phongbaoto.vnstormbook.utils.UtilsComponent.ButtonComponent
import com.phongbaoto.vnstormbook.utils.UtilsComponent.Space
import com.phongbaoto.vnstormbook.utils.data
import com.phongbaoto.vnstormbook.utils.like


@Composable
fun ListChapterStory(
    listChapter: List<Chapter>,
    navController: NavController,
    isShowButton: Boolean
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black)
    ){
        RowContent(
            title = "Danh sách chương: ",
            icon = data,
            content = "",
            width = 150.dp
        )
        //list chapter
        Space(10.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Black)
                .height(360.dp)
                .border(
                    width = 2.dp,
                    color = White,
                    shape = RoundedCornerShape(5.dp)
                )

        ){

            //list o day
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(355.dp)
                    .padding(start = 10.dp, end = 10.dp, bottom = if(isShowButton) 50.dp else 10.dp, top = 5.dp)
            ){
                items(listChapter){ item ->
                    ItemChapter(
                        chapter = item,
                        onClick = {
//                            navController.navigate("${ROUTER.DetailStory.name}/${false}")
                        }
                    )
                }
            }

            //button
            if(isShowButton){
                ButtonComponent(
                    title = "Thêm chương mới",
                    color = BlueButton,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .align(Alignment.BottomEnd),
                    textColor = White,
                    image = like,
                    fontWeight = FontWeight.Medium,
                    isImage = false,
                    shape = 0.dp,
                    fontSize = 16.sp,
                    sizeIcon = 20.dp,
                    onClick = {
                        navController.navigate(ROUTER.AddChapter.name)
                    },
                )
            }
        }

    }
}


@Composable
fun ItemChapter(
    chapter: Chapter,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 5.dp)
            .background(color = Black)
            .clickable {
                onClick()
            }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Black)
                .padding(horizontal = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Row(){
                Text(
                    text = "Chương: ",
                    fontSize = 16.sp,
                    color = White,
                    fontWeight = FontWeight.Medium
                )
                Space(5.dp)
                Text(
                    text = chapter.chapterNumber.toString(),
                    fontSize = 16.sp,
                    color = White,
                    fontWeight = FontWeight.Medium
                )

            }

            if(chapter.title != null){
                Text(
                    text = chapter.title,
                    fontSize = 16.sp,
                    color = White,
                    fontWeight = FontWeight.Medium
                )
            }

            Text(
                text = FunUtils.convertDateTime(chapter.time),
                fontSize = 16.sp,
                color = White,
                fontWeight = FontWeight.Medium
            )
        }
        Space(5.dp)
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = White,
            thickness = 1.dp
        )

    }
}

