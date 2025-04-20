package com.phongbaoto.stormbook.ui.detailStoryAdmin.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.ui.detailStoryAdmin.DetailStoryAdmin
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.ui.theme.BlueButton_2
import com.phongbaoto.stormbook.ui.theme.RedButton
import com.phongbaoto.stormbook.ui.theme.White
import com.phongbaoto.stormbook.utils.UtilsComponent.ButtonComponent
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