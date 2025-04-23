package com.phongbaoto.stormbook.ui.adminUI.addChapterStory.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PhotoCamera
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.BlueButton
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.UtilsComponent.Space


@Composable
fun SelectMultipleImages(
    initialImages: List<Uri> = emptyList(),
    onImagesSelected: (List<Uri>) -> Unit
) {
    var selectedImageUris by remember { mutableStateOf<List<Uri>>(initialImages) }
    val context = LocalContext.current

    // Launcher để chọn hình ảnh từ thư viện
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val updatedList = selectedImageUris + it
            selectedImageUris = updatedList
            onImagesSelected(updatedList) // Trả về danh sách Uri cho component cha
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
            .background(color = Black)
            .drawBehind {
                val borderWidth = 4.dp.toPx() //độ rộng của viền
                val dashWidth = 5.dp.toPx()//độ dài của nét đứt
                val gapWidth = 5.dp.toPx()//khoảng cách giữa các nét đứt
                val radius = 10.dp.toPx()//độ cong của góc
                //vẽ viền nét đứt xung quanh
                drawRoundRect(
                    color = White,
                    size = size.copy(
                        width = size.width - borderWidth,
                        height = size.height - borderWidth
                    ),
                    style = Stroke(
                        width = borderWidth,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(
                                dashWidth,
                                gapWidth
                            )
                        )
                    ),
                    cornerRadius = CornerRadius(radius, radius)
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Space(15.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Ảnh cho truyện",
                color = White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Column(
                modifier = Modifier
                    .size(110.dp)
                    .drawBehind {
                        val borderWidth = 4.dp.toPx() //độ rộng của viền
                        val dashWidth = 5.dp.toPx()//độ dài của nét đứt
                        val gapWidth = 5.dp.toPx()//khoảng cách giữa các nét đứt
                        val radius = 10.dp.toPx()//độ cong của góc
                        //vẽ viền nét đứt xung quanh
                        drawRoundRect(
                            color = BlueButton,
                            size = size.copy(
                                width = size.width - borderWidth,
                                height = size.height - borderWidth
                            ),
                            style = Stroke(
                                width = borderWidth,
                                pathEffect = PathEffect.dashPathEffect(
                                    floatArrayOf(
                                        dashWidth,
                                        gapWidth
                                    )
                                )
                            ),
                            cornerRadius = CornerRadius(radius, radius)
                        )
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        galleryLauncher.launch("image/*")
                    },
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.PhotoCamera,
                        contentDescription = "camera",
                        tint = White,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }

        Space(5.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .height(205.dp)
        ) {
            if (selectedImageUris.isNotEmpty()) {
                // Hiển thị danh sách hình ảnh
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(selectedImageUris) { uri ->
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(190.dp)
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(uri)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Selected Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(10.dp))
                            )

                            // Nút xóa ảnh
                            IconButton(
                                onClick = {
                                    val updatedList = selectedImageUris.filter { it != uri }
                                    selectedImageUris = updatedList
                                    onImagesSelected(updatedList)
                                },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .size(36.dp)
                                    .background(
                                        color = Color.Black.copy(alpha = 0.5f),
                                        shape = CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Xóa ảnh",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            } else {
                // Placeholder khi chưa có ảnh
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.DarkGray.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Chưa có ảnh được chọn",
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Hiển thị số lượng ảnh đã chọn
        if (selectedImageUris.isNotEmpty()) {
            Text(
                text = "Đã chọn ${selectedImageUris.size} ảnh",
                color = White,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}