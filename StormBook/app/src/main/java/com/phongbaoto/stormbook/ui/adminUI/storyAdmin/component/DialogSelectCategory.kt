package com.phongbaoto.stormbook.ui.adminUI.storyAdmin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.phongbaoto.stormbook.data.model.Category
import com.phongbaoto.stormbook.ui.main.component.SearchComponent
import com.phongbaoto.stormbook.ui.theme.BlueButton_2
import com.phongbaoto.stormbook.ui.theme.DialogColor
import com.phongbaoto.stormbook.ui.theme.RedButton
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.UtilsComponent.ButtonDialog
import com.phongbaoto.stormbook.utils.UtilsComponent.SearchFieldComponent

@Composable
fun DialogSelectCategory(
    showDialog: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit
) {
    var searchCategory by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val listCategory = listOf(
        Category(1, "Manhua"), Category(2, "Manhwa"), Category(3, "Action"), Category(4, "Lmao")
    )
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss, properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier
                    .background(DialogColor, shape = RoundedCornerShape(10.dp))
                    .padding(15.dp)
                    .fillMaxWidth(0.95f)
            ) {
                Column(
                    modifier = Modifier
                ) {
                    SearchFieldComponent(
                        value = searchCategory,
                        onValueChange = { search ->
                            searchCategory = search
                        },
                        isFocused = remember { mutableStateOf(isFocused) },
                        placeholder = "Nhập thể loại muốn tìm..."
                    )

                    ListCategory(
                        listCategory
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
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