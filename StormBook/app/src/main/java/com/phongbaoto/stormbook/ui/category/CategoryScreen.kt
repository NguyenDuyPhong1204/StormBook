package com.phongbaoto.stormbook.ui.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phongbaoto.stormbook.data.model.Category
import com.phongbaoto.stormbook.ui.category.component.DialogAddCategory
import com.phongbaoto.stormbook.ui.category.component.ListCategory
import com.phongbaoto.stormbook.ui.theme.Black
import com.phongbaoto.stormbook.utils.UtilsComponent.HeaderComponent
import com.phongbaoto.stormbook.utils.UtilsComponent.HideStatusBar
import com.phongbaoto.stormbook.utils.plus

@Composable
fun CategoryScreen(
    navController: NavController
){
    val listCategory = listOf(
        Category(1, "Manhua"),
        Category(2, "Manhwa"),
        Category(3, "Action"),
        Category(4,"Lmao")
    )

    var showDialog by remember { mutableStateOf(false) }

   Column(
       modifier = Modifier
           .fillMaxSize()
           .background(color = Black)
           .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
//           .statusBarsPadding()
   ){

//       HideStatusBar()

       HeaderComponent(
           title = "Danh sách thể loại",
           onClick = {
               showDialog = true
           },
           navController = navController,
           rightIcon = plus
           )
       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(start = 15.dp, end = 15.dp)
       ) {
            ListCategory(
                listCategory = listCategory
            )

           if(showDialog){
                DialogAddCategory(
                    showDialog = showDialog,
                    onDismiss = {
                        showDialog = false
                    },
                    onConfirm = {}
                )
           }

       }
   }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategory(){
    val navController = rememberNavController()
    CategoryScreen(
        navController
    )
}