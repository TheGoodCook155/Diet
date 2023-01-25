package com.diet.navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.diet.AppTopBar
import com.diet.viewmodel.DietViewModel

@Composable
fun AddProductScreen(
    viewModel: DietViewModel,
    mainScreen: Boolean,
    navController: NavHostController
) {

    Column(modifier = Modifier.padding(1.dp).fillMaxSize()) {

        AppTopBar(navController = navController, isMainScreen = false, "Add Products")

        Text(text = "Add product screen")

        /*
          val productName : String,
    val individualCalories: Int,
    val mealName: String
         */

    }
    
}