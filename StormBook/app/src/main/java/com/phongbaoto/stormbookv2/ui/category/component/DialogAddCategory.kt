package com.phongbaoto.stormbookv2.ui.category.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.phongbaoto.stormbookv2.ui.theme.BlueButton_2
import com.phongbaoto.stormbookv2.ui.theme.DialogColor
import com.phongbaoto.stormbookv2.ui.theme.RedButton
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.UtilsComponent.button.ButtonDialog
import com.phongbaoto.stormbookv2.utils.UtilsComponent.Space
import com.phongbaoto.stormbookv2.utils.UtilsComponent.textField.TextFieldComponent

@Composable
fun DialogAddCategory(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
){
    var categoryName by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    if(showDialog){
        Dialog(
            onDismissRequest = onDismiss
        ) {
            Box(
                modifier = Modifier
                    .background(DialogColor, shape = RoundedCornerShape(10.dp))
                    .padding(16.dp)
            ){
                Column(){
                    Text(
                        text = "Thêm mới thể loại",
                        color = White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Space(10.dp)
                    Text(
                        text = "Tên thể loại",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = White
                    )
                    Space(5.dp)
                    TextFieldComponent(
                        value = categoryName,
                        onValueChange = {name ->
                            categoryName = name
                        },
                        placeholder = "Nhập tên thể loại",
                        isFocused = remember { mutableStateOf(isFocused)},
                    )
                    Space(10.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        ButtonDialog(
                            title = "Hủy",
                            color = RedButton,
                            onClick = onDismiss,
                            textColor = White,
                            fontWeight = FontWeight.Bold,
                            width = 85.dp,
                            height = 35.dp
                        )
                        ButtonDialog(
                            title = "Xác nhận",
                            color = BlueButton_2,
                            onClick = onConfirm,
                            textColor = White,
                            fontWeight = FontWeight.Bold,
                            width = 100.dp,
                            height = 35.dp
                        )
                    }

                }
            }
        }
    }
}