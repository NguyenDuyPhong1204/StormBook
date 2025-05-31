package com.phongbaoto.vnstormbook.ui.adminUI.storyAdmin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.vnstormbook.ui.theme.Black
import com.phongbaoto.vnstormbook.ui.theme.BlueInput
import com.phongbaoto.vnstormbook.ui.theme.PlaceHold
import com.phongbaoto.vnstormbook.ui.theme.White
import com.phongbaoto.vnstormbook.utils.UtilsComponent.Space


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCategoryField(
    title: String,
    onShowDialog: () -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
            Space(3.dp)
            Text(
                text = "*",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }
        Space(5.dp)
        TextField(
            value = value,
            onValueChange = { newValue -> onValueChange(newValue) },
            placeholder = { Text(text = placeholder, color = PlaceHold) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .focusable()
                .clickable {
                    onShowDialog()
                },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = BlueInput
            ),
            shape = RoundedCornerShape(10.dp),
            // Thêm trailing icon cho password
            trailingIcon = {
             IconButton(
                 onClick = onShowDialog,
                 modifier = Modifier.size(20.dp)
             ) {
                 Icon(
                     imageVector = Icons.Default.KeyboardArrowDown,
                     contentDescription = null,
                     tint = Black
                 )
             }
            },
            enabled = false,

        )

    }
}
