package com.phongbaoto.stormbookv2.ui.adminUI.detailStoryAdmin.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.BlueButton_2
import com.phongbaoto.stormbookv2.ui.theme.RedButton
import com.phongbaoto.stormbookv2.ui.theme.White

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ConfirmComponent(
    onReject: () -> Unit,
    onConfirm: () -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black)
    ){
        ButtonStory(
            title = "Từ chối",
            color = RedButton,
            fontWeight = FontWeight.Medium,
            textColor = White,
            onClick = {onReject()}
        )

        ButtonStory(
            title = "Chấp nhận",
            color = BlueButton_2,
            fontWeight = FontWeight.Medium,
            textColor = White,
            onClick = {onConfirm()}
        )
    }
}