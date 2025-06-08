package com.phongbaoto.stormbookv2.ui.chapter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.phongbaoto.stormbookv2.ui.theme.BlueButton
import com.phongbaoto.stormbookv2.ui.theme.DialogColor
import com.phongbaoto.stormbookv2.ui.theme.RedWhite
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.UtilsComponent.button.ButtonDialog
import com.phongbaoto.stormbookv2.utils.UtilsComponent.Space
import com.phongbaoto.stormbookv2.utils.UtilsComponent.textField.TextFieldComponent


@Composable
fun DialogReport(
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    var selectedError by remember { mutableStateOf("") }
    var errorDescription by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var showType by remember { mutableStateOf(false) }
    val errorTypes = listOf(
        "-- Chọn loại lỗi --",
        "Ảnh lỗi, không thấy ảnh",
        "Chương bị trùng",
        "Chương chưa dịch",
        "Sai truyện",
        "Lỗi khác"
    )
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                usePlatformDefaultWidth =  false
            )
        ) {
            Box(
                modifier = Modifier
                    .background(DialogColor, shape = RoundedCornerShape(10.dp))
                    .padding(15.dp)
                    .fillMaxWidth(0.95f)
            ) {
                Column(
//                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "BÁO LỖI",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Space(10.dp)
                    SelectTypeReport(
                        title = "Loại lỗi",
                        value = selectedError,
                        onValueChange = {
                            selectedError = it
                        },
                        placeholder = "-- Chọn loại lỗi --",
                        options = errorTypes
                    )

                    Space(5.dp)
                    Text(
                        text = "Mô tả chi tiết lỗi để có thể được fix sớm nhất",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = White
                    )
                    Space(5.dp)
                    TextFieldComponent(
                        value = errorDescription,
                        onValueChange = {
                            errorDescription = it
                        },
                        placeholder = "Mô tả....",
                        isFocused = remember { mutableStateOf(isFocused) }
                    )
                    Space(10.dp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        ButtonDialog(
                            title = "Hủy",
                            color = RedWhite,
                            onClick = {
                                onDismiss()
                            },
                            textColor = White,
                            fontWeight = FontWeight.Bold,
                            width = 90.dp,
                            height = 37.dp
                        )
                        Space(10.dp)
                        ButtonDialog(
                            title = "Gửi",
                            color = BlueButton,
                            onClick = {},
                            textColor = White,
                            fontWeight = FontWeight.Bold,
                            width = 90.dp,
                            height = 37.dp
                        )
                    }

                }
            }
        }
    }
}