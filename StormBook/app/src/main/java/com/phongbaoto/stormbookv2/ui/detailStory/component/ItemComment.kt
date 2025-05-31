package com.phongbaoto.stormbookv2.ui.detailStory.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.phongbaoto.stormbookv2.data.model.Comment
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.BlueButton
import com.phongbaoto.stormbookv2.ui.theme.GrowComment
import com.phongbaoto.stormbookv2.ui.theme.OrangeComment
import com.phongbaoto.stormbookv2.ui.theme.White
import com.phongbaoto.stormbookv2.utils.FunUtils
import com.phongbaoto.stormbookv2.utils.UtilsComponent.Space
import com.phongbaoto.stormbookv2.utils.comment_2
import com.phongbaoto.stormbookv2.utils.like

@Composable
fun ItemComment(
    comment: Comment
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(color = Black),
//        verticalAlignment = Alignment.CenterVertically
    ) {
        //avatar
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = GrowComment)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(comment.avatar)
                    .crossfade(true)
                    .build(),
                contentDescription = "avatar",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(50.dp)
            )
        }
        Space(10.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 5.dp)
        ) {
                Text(
                    text = comment.nameUser,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = White
                )
                Space(2.dp)
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = OrangeComment,
                    thickness = 1.dp
                )
                Space(3.dp)
                Text(
                    text = comment.comment,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = White
                )
            Space(5.dp)
            //cac nut like cac thu
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                //like voi comment
                Row{
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(13.dp)
                    ) {
                        Icon(
                            painter = painterResource(like),
                            contentDescription = "Like",
                            tint = BlueButton,
                            modifier = Modifier
                                .size(12.dp)
                        )
                    }
                    Space(2.dp)
                    Text(
                        text = comment.countLike.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = BlueButton
                    )
                    Space(5.dp)
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(13.dp)
                    ) {
                        Icon(
                            painter = painterResource(comment_2),
                            contentDescription = "comment",
                            tint = BlueButton,
                            modifier = Modifier
                                .size(12.dp)
                        )
                    }
                    Space(2.dp)
                    Text(
                        text = " Trả lời",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = BlueButton
                    )
                }

                //gio comment
                Text(
                    text = FunUtils.formatRelativeTime(comment.timeComment),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = White
                )

            }
        }

    }
}