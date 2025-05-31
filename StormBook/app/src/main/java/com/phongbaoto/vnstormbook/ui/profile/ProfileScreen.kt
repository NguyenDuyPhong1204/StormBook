package com.phongbaoto.vnstormbook.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.vnstormbook.ui.profile.component.InfoUser
import com.phongbaoto.vnstormbook.ui.profile.component.MenuSelect
import com.phongbaoto.vnstormbook.ui.profile.data.ListMenu
import com.phongbaoto.vnstormbook.ui.theme.Black
import com.phongbaoto.vnstormbook.ui.theme.GrowComment
import com.phongbaoto.vnstormbook.utils.UtilsComponent.ColorBackground
import com.phongbaoto.vnstormbook.utils.UtilsComponent.Space

@Preview
@Composable
fun PreviewProfile() {
    ProfileScreen(rememberNavController())
}

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val uri = "https://cdn-0001.qstv.on.epicgames.com/lSxoPBCDpPvtmkeBQD/image/landscape_comp.jpeg"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = Black)
        ) {
            //mau nen
            ColorBackground()
            //thong tin
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .height(80.dp)
            ) {
                InfoUser()
            }
        }
        //menu
        Space(15.dp)
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth()
                .background(color = Black)
                .clip(RoundedCornerShape(10.dp)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp) //neu la admin thi la 350 con neu la user thi la 310
                    .background(color = GrowComment)
                    .clip(RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.Center
            ){
                MenuSelect(
                    listItem = ListMenu.listMenuAdmin
                )
            }
        }
    }
}