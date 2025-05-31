package com.phongbaoto.stormbookv2.utils.UtilsComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.BlueButton_2
import com.phongbaoto.stormbookv2.ui.theme.White

@Composable
fun NotifyDialogComponent(
    showDialog: Boolean,
    onClick: () -> Unit,
    contentNotify: String,
    textButton: String
){
    if(showDialog){
        Dialog(
            onDismissRequest = onClick
        ) {
            Box(
                modifier = Modifier
                    .background(color = White, shape = RoundedCornerShape(10.dp))
                    .padding(15.dp)
                    .height(150.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Thông báo",
                        color = Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    //content
                    Space(5.dp)
                    Text(
                        text = contentNotify,
                        color = Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        softWrap = true,
                        maxLines = 3
                    )
                    //ok
                    Space(5.dp)
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ){
                    ButtonComponent(
                        title = textButton,
                        color = BlueButton_2,
                        textColor = White,
                        image = null,
                        isImage = false,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        shape = 5.dp,
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth(),
                        onClick = {
                            onClick()
                        }
                    )
                }
            }
        }
    }
}