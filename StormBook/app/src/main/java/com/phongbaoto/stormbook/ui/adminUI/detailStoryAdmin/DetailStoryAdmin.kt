package com.phongbaoto.stormbook.ui.adminUI.detailStoryAdmin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.ui.adminUI.detailStoryAdmin.component.ConfirmComponent
import com.phongbaoto.stormbook.ui.adminUI.detailStoryAdmin.component.DialogRejectStory
import com.phongbaoto.stormbook.ui.adminUI.detailStoryAdmin.component.InfoStory
import com.phongbaoto.stormbook.ui.adminUI.detailStoryAdmin.component.IntroduceStory
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.BlueButton_3
import com.phongbaoto.stormbook.ui.theme.GreenButton
import com.phongbaoto.stormbook.ui.theme.RedButton_3
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.UtilsComponent.ButtonComponent
import com.phongbaoto.stormbook.utils.UtilsComponent.Space
import com.phongbaoto.stormbook.utils.banner
import com.phongbaoto.stormbook.utils.like
import com.phongbaoto.stormbook.utils.love
import com.phongbaoto.stormbook.utils.story

@Preview(showBackground = true)
@Composable
fun PreView(){
    DetailStoryAdmin(navController = rememberNavController())
}

@Composable
fun DetailStoryAdmin(
    navController: NavController
) {
    var showDialog by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
            .padding(bottom = 50.dp)
            .verticalScroll(scrollState)
    ) {
        //image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            Image(
                painter = painterResource(banner),
                contentDescription = "coverImage",
                modifier = Modifier
                    .fillMaxSize()
            )

            IconButton(
                onClick = {navController.popBackStack()},
                modifier = Modifier
                    .size(30.dp)
                    .offset(x = 0.dp, y = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "back",
                    modifier = Modifier.size(20.dp),
                    tint = White
                )
            }
        }
        //information story
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            InfoStory()
        }
        //button
        Space(10.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                //doc tu dau
                ButtonComponent(
                    title = "Đọc từ đầu",
                    color = GreenButton,
                    modifier = Modifier
                        .height(50.dp),
                    textColor = White,
                    image = story,
                    fontWeight = FontWeight.Medium,
                    isImage = true,
                    shape = 5.dp,
                    fontSize = 16.sp,
                    sizeIcon = 20.dp,
                    onClick = {},
                )
                //theo doi
                ButtonComponent(
                    title = "Theo dõi",
                    color = RedButton_3,
                    modifier = Modifier
                        .height(50.dp),
                    textColor = White,
                    image = love,
                    fontWeight = FontWeight.Medium,
                    isImage = true,
                    shape = 5.dp,
                    fontSize = 16.sp,
                    sizeIcon = 20.dp,
                    onClick = {},
                )
            }
            //thich
            Space(10.dp)
            ButtonComponent(
                title = "Thích",
                color = BlueButton_3,
                modifier = Modifier
                    .height(50.dp)
                    .width(160.dp),
                textColor = White,
                image = like,
                fontWeight = FontWeight.Medium,
                isImage = true,
                shape = 5.dp,
                fontSize = 16.sp,
                sizeIcon = 20.dp,
                onClick = {},
            )
        }

        //gioi thieu
        Space(10.dp)
        IntroduceStory(
            nameStory = "Ta là Tà Đế"
        )
        //button
        Space(15.dp)
        ConfirmComponent(
            onReject = {
                showDialog = true
            },
            onConfirm = {}
        )
    }

    if(showDialog){
        DialogRejectStory(
            showDialog = showDialog,
            onConfirm = {},
            onDismiss = {
                showDialog = false
            }
        )
    }

}