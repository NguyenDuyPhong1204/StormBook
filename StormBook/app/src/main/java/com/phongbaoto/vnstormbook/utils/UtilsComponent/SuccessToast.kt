package com.phongbaoto.vnstormbook.utils.UtilsComponent

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.phongbaoto.vnstormbook.ui.theme.Black
import com.phongbaoto.vnstormbook.ui.theme.White
import com.phongbaoto.vnstormbook.utils.checked
import kotlinx.coroutines.delay

@Composable
fun SuccessToast(
    message: String,
    visible: Boolean,
    onDismiss: () -> Unit
) {
    val transition = updateTransition(targetState = visible, label = "bannerTransition")
    val alpha by transition.animateFloat(label = "alpha") { state ->
        if (state) 1f else 0f
    }

    if (visible || alpha > 0f) {
        LaunchedEffect(Unit) {
            delay(3000) // hiển thị 3 giây
            onDismiss()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .alpha(alpha),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(White)
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(checked),
                    contentDescription = "Success",
//                    tint = Color(0xFF4CAF50), // xanh lá
                    modifier = Modifier.size(25.dp)
                )
                Space(8.dp)
                Text(
                    text = message,
                    color = Black,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}