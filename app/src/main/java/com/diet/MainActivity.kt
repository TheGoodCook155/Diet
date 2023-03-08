package com.diet

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.diet.model.*
import com.diet.model.references.MealsWithProductsCrossRef
import com.diet.model.references.ProductsInMeals
import com.diet.navigation.AddMealScreenDestination
import com.diet.navigation.AddProductScreenDestination
import com.diet.navigation.HomeScreenDestination
import com.diet.navigation.Navigation
import com.diet.ui.theme.DietTheme
import com.diet.viewmodel.DietViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.nio.file.WatchEvent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


        val viewModel: DietViewModel = hiltViewModel()
            DietTheme {
//                insertDummyData(viewModel = viewModel)
            App()

            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun App() {

    val controller = rememberNavController()

        Navigation(controller)


}


@Composable
fun AppTopBar(navController: NavController, isMainScreen: Boolean, title: String) {


    TopAppBar(modifier = Modifier
        .fillMaxWidth()
        .background(colorResource(id = R.color.primary)),
        elevation = 2.dp) {


        Row(modifier = Modifier
            .background(colorResource(id = R.color.primary))
            .padding(1.dp)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            Row(modifier = Modifier.padding(15.dp)) {

                if(!isMainScreen){
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back" ,
                        modifier = Modifier
                            .width(30.dp)
                            .padding(top = 2.dp)
                            .clickable {
                                Log.d("GoBack", "AppTopBar: Clicked")
                                navController.popBackStack()
                            })
                }
                else{
                    Spacer(modifier = Modifier.padding(15.dp))
                }
                Text(text = title, style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .paddingFromBaseline(top = 20.dp))
            }

            Row(modifier = Modifier.padding(15.dp)) {
                IconButton(onClick = {

                    navController.navigate(AddMealScreenDestination.route) {
                        popUpTo(HomeScreenDestination.route)
                    }

                }, modifier = Modifier
                    .height(50.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(colorResource(id = R.color.lightPrimary))
                    .border(1.dp, Color.White, RoundedCornerShape(5.dp))
                ) {
                    Row(modifier = Modifier.padding(2.dp)) {

                        Image(painter = painterResource(id = R.drawable.meal_icon), contentDescription = "Add meal",
                            modifier = Modifier
                                .height(25.dp)
                                .width(25.dp))

                        Icon(Icons.Default.AddCircle, contentDescription = "Add meal icon")
                    }
                }

                Spacer(Modifier.width(5.dp))

                IconButton(onClick = {

                    navController.navigate(AddProductScreenDestination.route) {
                        popUpTo(HomeScreenDestination.route)
                    }


                }, Modifier
                    .height(50.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(colorResource(id = R.color.lightPrimary))
                    .border(1.dp, Color.White, RoundedCornerShape(5.dp))
                ){
                    Row(modifier = Modifier.padding(2.dp)) {

                        Image(painter = painterResource(id = R.drawable.product_icon), contentDescription = "Add product",
                            modifier = Modifier
                                .height(25.dp)
                                .width(25.dp))

                        Icon(Icons.Default.AddCircle, contentDescription = "Add product icon")
                    }
                }

            }

        }
    }

}


@Composable
fun insertDummyData(viewModel: DietViewModel){


    val products = listOf<Product>(
        Product("Banani",20),
        Product("Kromid",40),
        Product("Zelka",50),
        Product("Meso",120),
        Product("Stek",200),
        Product("Kavijar",150),
        Product("Testo",200),
        Product("Pivo",200),
        Product("Jagodi",200),
        Product("Creshi",20),
        Product("Slivi",200),
        Product("Borovinki",200),
        Product("Grav",200),




        )

    val mealsWithProductCrossRef = listOf<MealsWithProductsCrossRef>(

        MealsWithProductsCrossRef("ovosna salata","Borovinki"),
        MealsWithProductsCrossRef("ovosna salata","Jagodi"),
        MealsWithProductsCrossRef("sarma","Zelka"),
        MealsWithProductsCrossRef("rasol","Zelka"),
        MealsWithProductsCrossRef("Uzinka","Stek"),
        MealsWithProductsCrossRef("Kinesko","Stek"),
        MealsWithProductsCrossRef("grav so kolbasi","Grav"),
        MealsWithProductsCrossRef("posen grav","Grav"),
        MealsWithProductsCrossRef("mesana salata","Borovinki"),


        )


    val meal = listOf<Meal>(
        Meal("ovosna salata","Ponedelnik"),
        Meal("Sarma","Ponedelnik"),
        Meal("GrckaSalata","Ponedelnik"),
        Meal("salata","Vtornik"),
        Meal("grav so kolbasi","Vtornik"),
        Meal("kafe","Vtornik"),
        Meal("Uzinka","Vtornik"),
        Meal("Kinesko","Vtornik"),
        Meal("mesana salata","Vtornik"),

        Meal("sarma","Sreda"),
        Meal("sladoled","Sreda"),
        Meal("pivo","Sreda"),

        Meal("sopska salata","Cetvrtok"),
        Meal("polneti piperki","Cetvrtok"),
        Meal("Tuna","Cetvrtok"),

        Meal("kafe","Petok"),
        Meal("topla rakija","Petok"),
        Meal("ruska salata","Petok"),

        Meal("salata","Sabota"),
        Meal("rakija","Sabota"),
        Meal("pivo","Sabota"),
        Meal("Pizza","Sabota"),

        Meal("Kafe","Nedela"),
        Meal("Javnija","Nedela"),
        Meal("Zelencuk","Nedela")

    )


    val days = listOf<Day>(
        Day("Ponedelnik",123456),
        Day("Vtornik",123456),
        Day("Sreda",123456),
        Day("Cetvrtok",123456),
        Day("Petok",123456),
        Day("Sabota",123456),
        Day("Nedela",123456)
    )


    products.forEach {

        viewModel.insertProduct(it)

    }

    meal.forEach{

        viewModel.insertMeal(it)
    }

    days.forEach {
        viewModel.insertDay(it)
    }

    mealsWithProductCrossRef.forEach {
       viewModel.insertMealsWithProductsCrossRef(it)
    }


}





