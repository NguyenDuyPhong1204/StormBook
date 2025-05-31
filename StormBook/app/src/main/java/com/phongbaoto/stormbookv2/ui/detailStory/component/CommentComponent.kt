package com.phongbaoto.stormbookv2.ui.detailStory.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phongbaoto.stormbookv2.data.model.Comment
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.OrangeComment
import com.phongbaoto.stormbookv2.utils.UtilsComponent.Space
import com.phongbaoto.stormbookv2.utils.UtilsComponent.TextFieldComponent
import com.phongbaoto.stormbookv2.utils.comment

@Composable
fun CommentComponent(
    listComment: List<Comment>
){
    var textComment by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(color = Black)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Black),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .width(150.dp)
            ){
                Image(
                    painter = painterResource(id = comment),
                    contentDescription = "Image",
                    modifier = Modifier.size(25.dp),
                )
                Space(5.dp)
                Text(
                    text = "Bình luận (${1234})",
                    color = OrangeComment,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        //text field comment
        Space(5.dp)
        TextFieldComponent(
            value = textComment,
            onValueChange = {comment ->
                textComment = comment
            },
            isFocused = remember { mutableStateOf(isFocused) },
            placeholder = "Khi màn đêm buông xuống, cũng là lúc mà nỗi buồn vây " +
                    "kín trong anh. Dẫu biết anh chẳng là gì cả nhưng với anh em như " +
                    "vầng trăng sáng soi tỏa ánh ban đêm (┬┬﹏┬┬).",
            modifier = Modifier
                .height(150.dp)
        )
        //list comment
        LazyColumn(
            modifier = Modifier
                .height(200.dp)
        ){
            items(listComment){ item ->
                ItemComment(
                    comment = item
                )
            }
        }
    }
}
