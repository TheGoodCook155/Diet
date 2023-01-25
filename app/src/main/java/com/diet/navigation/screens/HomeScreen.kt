package com.diet.navigation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diet.AppTopBar
import com.diet.R
import com.diet.model.Meal
import com.diet.model.Product
import com.diet.model.references.DayWithMeals
import com.diet.model.references.MealWithProducts
import com.diet.utils.formatDate
import com.diet.viewmodel.DietViewModel
import java.nio.file.WatchEvent


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(viewModel: DietViewModel, mainScreen: Boolean, navController: NavController){


    val days = viewModel.getAllDays.collectAsState()
    val getDayWithMealsNoParam = viewModel.getDayWithMealsNoParam.collectAsState()
    Log.d("DATA", "MainScreen: ${getDayWithMealsNoParam.value}")

    Column(modifier = Modifier
        .padding(1.dp)
        .fillMaxSize()) {

        AppTopBar(navController = navController, isMainScreen = true,"Home")

        if (getDayWithMealsNoParam.value.isEmpty() || getDayWithMealsNoParam.value.isNullOrEmpty()){

            Text(text = "No entries found.",  style = MaterialTheme.typography.h3, modifier = Modifier.padding(start = 5.dp))

        }else{

            LazyColumn{
                itemsIndexed(getDayWithMealsNoParam.value){ index,dayWithMeal ->

//            Log.d("DATA", "MainScreen: index: ${index}, day: ${dayWithMeal}, productsInMeal: ${productsInMeal.value}")
                    viewModel.getMealsWithProducts(dayWithMeal.day.toString())
                    CreateDayMealCard(dayWithMeal,viewModel)

                }
            }

        }



    }

}



@Composable
fun CreateDayMealCard(
    dayWithMeals: DayWithMeals,
    viewModel: DietViewModel
) {
    Log.d("DATA", "CreateDayMealCard: ENTERED")

    Log.d("DATA", "CreateDayMealCard: day: ${dayWithMeals.day} Meals: ${dayWithMeals.meals}")

    val meals = dayWithMeals.meals

    val calConst = stringResource(id = R.string.kalorii)
    val mealNameSaveable = rememberSaveable {
        mutableStateOf("")
    }


    Card(modifier = Modifier
        .clickable { }
        .padding(5.dp)
        .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)),
        backgroundColor = colorResource(R.color.lightPrimary),
        elevation = 5.dp) {

        Row(modifier = Modifier.padding(2.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start) {

            Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier
                .size(150.dp, 150.dp)
                .padding(5.dp))

            Spacer(modifier = Modifier.width(5.dp))

            Column(modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {


                Text(text = formatDate(dayWithMeals.day.date.toInt()), style = MaterialTheme.typography.h6, color = colorResource(
                    id = R.color.secondaryText
                )
                )
                Text(text = dayWithMeals.day.dayName.toString(), style = MaterialTheme.typography.h4)


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp)
                    .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.SpaceEvenly) {

                    for (i in 0..meals.size-1){
                        var mealName = meals.get(i).mealName


                        MealCard(mealName,{
                            mealNameSaveable.value = it
                            viewModel.getMealsWithProducts(it)
                        })
                    }

                    var mealsWithProducts : List<MealWithProducts> = viewModel.getMealsWithProducts.collectAsState().value
                    Log.d("mealsWithProducts", "CreateDayMealCard: ${mealsWithProducts.size}, content: ${mealsWithProducts.toString()}")

                    if (mealNameSaveable.value.toString().isNotEmpty() && mealNameSaveable.value.toString() != null){

                        OpenAlertBox(mealNameSaveable.value, mealsWithProducts){

                            if (it == true){

                                mealNameSaveable.value = "";

                            }

                        }

                    }
                }

            }

        }

    }

}

@Composable
fun MealCard(mealName: String,  mealNameCallback: (String) -> Unit) {

    Card(
        Modifier
            .padding(10.dp)
            .defaultMinSize(minWidth = 100.dp)
            .clickable {
                mealNameCallback(mealName)
            },
        elevation = 5.dp,
        backgroundColor = colorResource(id = R.color.primary),
        shape = RoundedCornerShape(10.dp)
    ) {

        Text(text = mealName,style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(5.dp),
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.textIcons)
        )
    }

}

@Composable
fun OpenAlertBox(mealSaveableName: String, mealsWithProductsList: List<MealWithProducts>, openStateCallBack : (Boolean) -> Unit){

    Log.d("AlertBox", "Entered")

    val alertBoxState = remember{
        mutableStateOf(false)
    }

    Log.d("AlertBox", " mealSaveableName: ${mealSaveableName} ingredientsSize: ${mealsWithProductsList.size}")


        lateinit var ingredients: List<Product>



    if (mealsWithProductsList.size == 0){
        ingredients = emptyList()
    }else{
        ingredients = mealsWithProductsList.get(0).products
    }

        Log.d("AlertBox", " ingredients: ${ingredients}")


    AlertDialog(onDismissRequest = {
        alertBoxState.value
        Log.d("AlertBox", "OpenAlertBox: alertBoxState.value: ${alertBoxState.value}")
    }, buttons = {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {

            Button(onClick = {
                Log.d("AlertBox", "OpenAlertBox: Close Button clicked: ${alertBoxState.value}")
                alertBoxState.value = true
                openStateCallBack(true)
                Log.d("AlertBox", "OpenAlertBox: Close Button clicked retuning (always false): ${alertBoxState.value}")

            }, modifier = Modifier.padding(bottom = 5.dp)) {
                Text(text = "Close",modifier = Modifier.padding(5.dp))
            }

        }


    }, modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        title = {

            Text(text = mealSaveableName,
                color = colorResource(id = R.color.primaryText),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp)

        }, text = {


            Column(modifier = Modifier
                .fillMaxWidth()) {

                Text(text = "Ingredients:\n",
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth(),
                    color = colorResource(id = R.color.primaryText),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp)

                ingredients.forEach {
                    Text(text = "${it.productName} - Calories: ${it.individualCalories} ",
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(),
                        color = colorResource(id = R.color.secondaryText),
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp)
                }
            }
        },
        shape = RectangleShape,
        backgroundColor = Color.LightGray


    )

    Log.d("AlertBox", "Exit")



}