package com.phongbaoto.stormbook.ui.adminUI.addChapterStory.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.UtilsComponent.Space
import com.phongbaoto.stormbook.utils.UtilsComponent.TextFieldComponent

@Composable
fun TextFieldChapter(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isFocused: MutableState<Boolean>,
    isRequired: Boolean
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
            if(isRequired){
                Text(
                    text = "*",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }

        }
        Space(5.dp)
        TextFieldComponent(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            isFocused = isFocused,
        )

    }
}