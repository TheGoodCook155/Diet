package com.diet.navigation.screens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.diet.AppTopBar
import com.diet.model.Meal
import com.diet.model.Product
import com.diet.model.references.MealsWithProductsCrossRef
import com.diet.viewmodel.DietViewModel
import java.time.LocalDate
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddMealScreen(viewModel: DietViewModel, mainScreen: Boolean, navController: NavController) {

    Log.d("TAG", "AddMealScreen: Entered")


    val mealName = rememberSaveable {
        mutableStateOf("")
    }

    val products = viewModel.getAllProducts.collectAsState().value

    val localFocusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val rowHeight = (products.size * 30)
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp

    Log.d("screenWidth", "AddMealScreen: ${screenWidth}")

    val receivedItems = remember {
        mutableStateListOf<Product>()
    }


    Log.d("transferredItems", "AddMealScreen: ${receivedItems.size} ")

    Log.d("rowHeight", "AddMealScreen: productsSize: ${products.size}")

    Log.d("rowHeight", "AddMealScreen: ${rowHeight}")


    Column(modifier = Modifier
        .padding(1.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        AppTopBar(navController = navController, isMainScreen = mainScreen, "Add Meals")


            TextField(value = mealName.value, onValueChange = {
                mealName.value = it
            },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        localFocusManager.moveFocus(FocusDirection.Down)
                        keyboard?.hide()
                    }
                ), modifier = Modifier.padding(20.dp),
                label = {
                    Text(text = "Meal name")
                })

         PickedProducts(receivedItems){ deleted->
            receivedItems.remove(deleted)
            Log.d("transferredItems", "AddMealScreen: deleted: ${deleted} ")
        }

        Log.d("transferredItems", "AddMealScreen: items: ${receivedItems.size} ")


            Row(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            ) {

                Column(modifier = Modifier
                    .weight(0.5f)
                    .border(2.dp, Color.LightGray, shape = RectangleShape),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(rowHeight.dp)
//                        .padding(2.dp)
                        .border(2.dp, Color.LightGray, RectangleShape)
                        .background(Color.Transparent)) {

                    }
                }

                Column(modifier = Modifier
                    .weight(0.5f)
                    .verticalScroll(rememberScrollState())
                    .border(2.dp, Color.LightGray, shape = RectangleShape),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally) {

                    
                    products.forEach { product ->
                        
                        ProductCard(product = product){ transferredItem ->

                            Log.d("transferredItems", "AddMealScreen: ${transferredItem}")
                            if(!receivedItems.contains(transferredItem)) {
                                receivedItems.add(transferredItem)
                            }
                            Log.d("transferredItems", "AddMealScreen: receivedItems: size: ${receivedItems.size}, items: ${receivedItems.toString()}")


                        }

                    }


                }

        //ROW
        }

        Button(onClick = {


           val saveMeal =  Meal(mealName.value,LocalDate.now().toString())

            receivedItems.forEach{ product ->

                val mealsWithProductsCrossRef = MealsWithProductsCrossRef(saveMeal.mealName,product.productName)

                viewModel.insertMealsWithProductsCrossRef(mealsWithProductsCrossRef)

            }

            viewModel.insertMeal(saveMeal)

            Toast.makeText(context,"Saved",Toast.LENGTH_LONG).show()

            //clear received items
            //clear input

            mealName.value = ""
            receivedItems.removeRange(0,receivedItems.size)

        }, modifier = Modifier.padding(2.dp)) {

            Text(text = "Save")

        }

    }

    Log.d("TAG", "AddMealScreen: Exit")

}

@Composable
fun ProductCard(product: Product, transferredItems: (Product) -> Unit){



    val productName = rememberSaveable {
        mutableStateOf(product.productName)
    }

    val offsetX = rememberSaveable {
        mutableStateOf(0f)
    }

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Log.d("screen", "ProductCard: height: ${screenHeight}, width: ${screenWidth} ")
    
    Card(modifier = Modifier
        .widthIn(100.dp)
        .height(30.dp)
        .padding(5.dp)
        .offset {
            IntOffset(offsetX.value.roundToInt(), 0)
        }
        .draggable(
            orientation = Orientation.Horizontal,
            state = rememberDraggableState({ delta ->
                offsetX.value += delta
                Log.d(
                    "offsetX",
                    "rememberDraggableState: ${offsetX.value.roundToInt()}, delta:${delta}"
                )

            }),
            enabled = true,
            onDragStarted = {
//                Log.d("offsetX", "ProductCard: ${offsetX.value.roundToInt()}")
            },
            onDragStopped = {
                Log.d("offsetX", "ProductCard: onDragStopped invoked")
                if (offsetX.value > -400) {
                    Log.d("offsetX", "ProductCard: onDragStopped offsetX.value > -400")
                    offsetX.value = 0F
                } else {
//                    transferredItems.add(productName.value)
                    transferredItems(product)
                    offsetX.value = 0F
//                    Log.d(
//                        "transferredItems",
//                        "ProductCard: callback: size ${transferredItems.size}, items: ${transferredItems.toString()}"
//                    )

                }
            }
        ),
        shape = RoundedCornerShape(5.dp),
        backgroundColor = Color.LightGray,
        elevation = 5.dp){
        
        Text(text = productName.value,
            textAlign = TextAlign.Center)
        
    }
    
    
}


@Composable
fun PickedProducts(
    receivedItems: SnapshotStateList<Product>,
    deleted: (Product) -> Unit
) {



    Row(modifier = Modifier
        .padding(3.dp)
        .horizontalScroll(rememberScrollState())) {

        receivedItems.forEach { product ->


            Card(modifier = Modifier.padding(2.dp),
                shape = RoundedCornerShape(5.dp),
                elevation = 5.dp) {

                Row(modifier = Modifier.padding(5.dp)) {

                    Text(text = product.productName)

                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete icon",
                        modifier = Modifier.clickable {

                            deleted(product)

                        })

                }

            }


        }

    }




}




