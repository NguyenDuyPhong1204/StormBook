package com.phongbaoto.stormbookv2.utils.UtilsComponent

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.phongbaoto.stormbookv2.ui.theme.Black
import com.phongbaoto.stormbookv2.ui.theme.BlueInput
import com.phongbaoto.stormbookv2.ui.theme.PlaceHold
import com.phongbaoto.stormbookv2.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isFocused: MutableState<Boolean>,
    isPassword: Boolean = false,
    isLeftIcon: Boolean = false,
    modifier: Modifier = Modifier
) {

    var passwordVisible = remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = { newValue -> onValueChange(newValue) },
        placeholder = { Text(text = placeholder, color = PlaceHold) },
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .border(
                width = 1.dp,
                color = if (isFocused.value) BlueInput else White,
                shape = RoundedCornerShape(10.dp)
            )
            .focusable()
            .onFocusChanged { focusState -> isFocused.value = focusState.isFocused },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = BlueInput
        ),
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (isPassword && !passwordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
        // Thêm trailing icon cho password
        trailingIcon = if (isPassword) {
            {
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(
                        imageVector = if (passwordVisible.value)
                            Icons.Outlined.Visibility
                        else
                            Icons.Outlined.VisibilityOff,
                        contentDescription = if (passwordVisible.value)
                            "Hide password"
                        else
                            "Show password"
                    )
                }
            }
        } else null,
        //icon ben trai
        leadingIcon = if (isLeftIcon) {
            {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }else null
    )
}