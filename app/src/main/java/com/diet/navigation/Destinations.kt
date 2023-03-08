package com.diet.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diet.navigation.screens.AddMealScreen
import com.diet.navigation.screens.AddProductScreen
import com.diet.navigation.screens.HomeScreen
import com.diet.viewmodel.DietViewModel



interface Destinations{
    val route: String
    val isMainScreen: Boolean
}

object HomeScreenDestination: Destinations{

    override val route: String = "HomeScreen"
    override val isMainScreen: Boolean = true


}

object AddMealScreenDestination: Destinations{

    override val route: String = "AddMealScreen"
    override val isMainScreen: Boolean = false

}

object AddProductScreenDestination: Destinations{

    override val route: String = "AddProductScreen"
    override val isMainScreen: Boolean = false


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(controller: NavHostController) {

    val viewModel = hiltViewModel<DietViewModel>()

    NavHost(navController = controller, startDestination = HomeScreenDestination.route){


        composable(HomeScreenDestination.route){

            HomeScreen(viewModel,HomeScreenDestination.isMainScreen, navController = controller)

        }

        composable(AddMealScreenDestination.route){

            AddMealScreen(viewModel,AddMealScreenDestination.isMainScreen, navController = controller)
        }

        composable(AddProductScreenDestination.route){

            AddProductScreen(viewModel,AddProductScreenDestination.isMainScreen, navController = controller)
        }
    }

}