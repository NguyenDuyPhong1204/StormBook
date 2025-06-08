package com.phongbaoto.stormbookv2.ui.chapter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phongbaoto.stormbookv2.ui.chapter.data.IconType
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.utils.UtilsComponent.Space
import com.phongbaoto.stormbookv2.utils.UtilsComponent.textField.TextFieldComponent
import com.phongbaoto.stormbookv2.utils.comment_3
import com.phongbaoto.stormbookv2.utils.like
import com.phongbaoto.stormbookv2.utils.plane
import com.phongbaoto.stormbookv2.utils.report

@Composable
fun NavigationChapter(
    onShowReportDialog: () -> Unit = {}
) {
    var comment by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .background(color = Black)
    ) {
        //comment
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextFieldComponent(
                value = comment,
                isFocused = remember { mutableStateOf(isFocused) },
                onValueChange = { comment = it },
                isPassword = false,
                placeholder = "Khi màn đêm buông xuống, cũng là lúc mà nỗi buồn vây " +
                        "kín trong anh....",
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            )
            Space(5.dp)
            //nut gui
            IconChapter(
                icon = IconType.Resource(plane),
                onClick = {},
                modifier = Modifier.size(35.dp)
            )
        }

        //cac thu nhu chuyen sang chuong khac, doc comment, bao cao, like
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //back
            IconChapter(
                icon = IconType.Vector(Icons.Default.ArrowBackIosNew),
                onClick = {},
                modifier = Modifier.size(25.dp)
            )
            //cac nut like, comment, bao cao
            Row(
                modifier = Modifier
                    .width(150.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //nut like
                IconChapter(
                    icon = IconType.Resource(like),
                    onClick = {},
                    modifier = Modifier.size(35.dp)
                )
                //nut comment
                IconChapter(
                    icon = IconType.Resource(comment_3),
                    onClick = {},
                    modifier = Modifier.size(35.dp)
                )
                //nut bao cao
                IconChapter(
                    icon = IconType.Resource(report),
                    onClick = {
                        onShowReportDialog()
                    },
                    modifier = Modifier.size(35.dp)
                )
            }

            //next
            IconChapter(
                icon = IconType.Vector(Icons.Default.ArrowForwardIos),
                onClick = {},
                modifier = Modifier.size(35.dp)
            )
        }
    }
}