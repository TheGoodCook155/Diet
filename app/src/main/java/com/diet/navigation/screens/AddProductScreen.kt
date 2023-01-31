package com.diet.navigation.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diet.AppTopBar
import com.diet.R
import com.diet.model.Product
import com.diet.viewmodel.DietViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddProductScreen(
    viewModel: DietViewModel,
    mainScreen: Boolean,
    navController: NavController
) {

    val keyboard = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current
    val context = LocalContext.current

    val productName = rememberSaveable {
        mutableStateOf("")
    }

    val individualCalories = rememberSaveable {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .padding(1.dp)
        .fillMaxSize()) {

        AppTopBar(navController = navController, isMainScreen = mainScreen, "Add Products")



        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = R.drawable.meal_icon),
                contentDescription = "Meal Icon",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth())


            TextField(value = productName.value, onValueChange = {
                productName.value = it
            },
                keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        localFocusManager.moveFocus(FocusDirection.Down)
                    }
                ), modifier = Modifier.padding(20.dp),
                label = {
                    Text(text = "Product name")
                })

            TextField(value = individualCalories.value.toString(), onValueChange = {
                individualCalories.value = it
            }, keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        localFocusManager.moveFocus(FocusDirection.Down)
                        keyboard?.hide()
                    }
                ), modifier = Modifier.padding(10.dp),
                label = {
                    Text(text = "Product calories")
                })

            Button(onClick = {

                Toast.makeText(context,"Saved: ProductName: ${productName.value}, productCalories: ${individualCalories.value}",Toast.LENGTH_LONG).show()

                val product = Product(productName.value,individualCalories.value.toInt())

                viewModel.insertProduct(product)

                productName.value = ""
                individualCalories.value = ""

            }, modifier = Modifier.padding(10.dp)) {

                Text(text = "Save Product")

            }

        }

    }

}

@Composable
@Preview(showBackground = true)
fun AddProductPreview(){

    val productName = rememberSaveable {
        mutableStateOf("")
    }

    val individualCalories = rememberSaveable {
        mutableStateOf(0)
    }

    Column(modifier = Modifier
        .padding(1.dp)
        .fillMaxSize()) {

        AppTopBar(navController = rememberNavController(), isMainScreen = false, "Add Products")



        Column(modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = painterResource(id = R.drawable.meal_icon),
                contentDescription = "Meal Icon",
                contentScale = ContentScale.FillWidth)


            TextField(value = productName.value, onValueChange = {
                productName.value = it
            }, modifier = Modifier.padding(20.dp))

            TextField(value = individualCalories.value.toString(), onValueChange = {
                individualCalories.value = it.toInt()
            }, modifier = Modifier.padding(20.dp))


            Button(onClick = { /*TODO*/ },
                modifier = Modifier.padding(20.dp)) {

                Text(text = "Save Product")

            }

        }

    }


}