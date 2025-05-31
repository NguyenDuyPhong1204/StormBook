package com.phongbaoto.stormbook.utils.UtilsComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.phongbaoto.stormbook.data.model.Story
import com.phongbaoto.stormbook.ui.theme.DialogColor
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.banner
import com.phongbaoto.stormbook.utils.banner_2
import com.phongbaoto.stormbook.utils.banner_3
import com.phongbaoto.stormbook.utils.banner_4

@Preview
@Composable
fun PreviewDialogSearch() {
//    SearchDialog(
//        showDialog = true,
//        onDismiss = {}
//    )
    val story = Story(1, banner_3, "Ta Là Tà Đế", 100)
    StoryItem(
        story = story,
        onClick = {}
    )
}

@Composable
fun SearchDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var isFocused by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val examList = listOf(
        Story(1, banner, "Ta Là Tà Đế", 200),
        Story(2, banner_2, "Ta Là Tà Đế", 300),
        Story(1, banner_3, "Ta Là Tà Đế", 100),
        Story(1, banner_4, "Ta Là Tà Đế", 250),
        Story(1, banner, "Ta Là Tà Đế", 200),
    )
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier
                    .background(color = DialogColor, shape = RoundedCornerShape(10.dp))
                    .padding(15.dp)
                    .height(300.dp)
                    .fillMaxWidth(0.95f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextFieldComponent(
                            value = searchText,
                            onValueChange = { searchValue ->
                                searchText = searchValue
                            },
                            placeholder = "Nhập tên truyện",
                            isFocused = remember { mutableStateOf(isFocused) },
                            isLeftIcon = true,
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                        )
                        //nut tat
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(30.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = White,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                        }
                    }
                    //danh sach truyen khi tim kiem
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ){
                        items(examList){
                            StoryItem(
                                story = it,
                                onClick = {}
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun StoryItem(
    story: Story,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(
                width = 1.dp,
                color = White,
                shape = RoundedCornerShape(10.dp)
            ),
    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(context = context)
//                .data(story.image)
//                .crossfade(true)
//                .build(),
//            contentDescription = "Image story",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .width(115.dp)
//                .height(105.dp)
//        )
        Image(
            painter = painterResource(banner),
            contentDescription = "coverImage",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(115.dp)
                .fillMaxHeight()
        )
        //thong tin truyen
        Space(5.dp)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //ten
            Text(
                text = story.name,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = White
            )
            //tac gia
            Text(
                text = "Tác giả: Updating",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = White
            )
            //chap moi nhat
            Text(
                text = "Chương: " + story.chapterNumber.toString(),
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = White
            )
        }
    }
}