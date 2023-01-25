package com.diet.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diet.model.*
import com.diet.model.references.DayWithMeals
import com.diet.model.references.MealWithProducts
import com.diet.repository.DietRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.migration.CustomInjection.inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
//TODO
@HiltViewModel
class DietViewModel @Inject constructor(private val repository: DietRepository ) : ViewModel() {



    init {
        getAllDays()
        getAllProducts()
        getAllMeals()
//        getDayWithMeals("Ponedelnik","123456")
        getMealsWithProducts("sarma")
        getDayWithMealsNoParam()

    }

    private var _getDayWithMealsNoParam = MutableStateFlow<List<DayWithMeals>>(emptyList())
    val getDayWithMealsNoParam = _getDayWithMealsNoParam.asStateFlow()

    private fun getDayWithMealsNoParam(){

        viewModelScope.launch(Dispatchers.IO) {
            repository.getDayWithMealsNoParam().collect{ getDayWithMeals ->
                _getDayWithMealsNoParam.value = getDayWithMeals
            }
        }

    }


    private var _getAllDays = MutableStateFlow<List<Day>>(emptyList())
    val getAllDays = _getAllDays.asStateFlow()


    private fun getAllDays(){

        viewModelScope.launch(Dispatchers.IO) {

          repository.getAllDays().collect{ day ->
              _getAllDays.value = day
          }

        }
//        Log.d("DATA", "getAllDays: DaysSize: ${getAllDays.value.size}, Days: ${getAllDays.value}")

    }

    private var _getAllProducts = MutableStateFlow<List<Product>>(emptyList())
    val getAllProducts = _getAllProducts.asStateFlow()

    fun getAllProducts(){

        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllProducts().collect{ product ->
                _getAllProducts.value = product
            }
        }
//        Log.d("DATA", "getAllProducts: ProductsSize: ${_getAllProducts.value.size}, Products: ${_getAllProducts.value}")


    }

    private var _getAllMeals = MutableStateFlow<List<Meal>>(emptyList())
    val getAllMeals = _getAllMeals.asStateFlow()

    fun getAllMeals(){

        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllMeals().collect{ meal ->
                _getAllMeals.value = meal
            }
        }
//        Log.d("DATA", "getAllMeals: Meals Size: ${_getAllMeals.value.size}, Meals: ${_getAllMeals.value}")

    }


    private var _getMealsWithProducts = MutableStateFlow<List<MealWithProducts>>(emptyList())
    val getMealsWithProducts = _getMealsWithProducts.asStateFlow()

    fun getMealsWithProducts(meal: String){

        viewModelScope.launch(Dispatchers.IO){
            repository.getMealsWithProducts(meal).collect{mealWithProducts ->
                _getMealsWithProducts.value = mealWithProducts
            }
        }
//        Log.d("DATA", "getMealsWithProducts: mealsWithProducts Size: ${_getMealsWithProducts.value.size}, mealsWithProducts: ${_getMealsWithProducts.value}")
    }


    private var _getDayWithMeals = MutableStateFlow<List<DayWithMeals>>(emptyList())
    val getDayWithMeals = _getDayWithMeals.asStateFlow()

    fun getDayWithMeals(day: String, date: String) : StateFlow<List<DayWithMeals>>{

        viewModelScope.launch(Dispatchers.IO){
            repository.getDayWithMeals(day,date).collect{dayWithMeals ->
                _getDayWithMeals.value = dayWithMeals
            }
        }
        return getDayWithMeals
//        Log.d("DATA", "getDayWithMeals: dayWithMeals Size: ${_getDayWithMeals.value.size}, dayWithMeals: ${_getDayWithMeals.value}")
    }

    //Product
    fun insertProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertProduct(product)
        }
    }

    fun updateProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateProduct(product)
        }
    }

    fun deleteProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteProduct(product)
        }
    }

    //Meal
    fun insertMeal(meal: Meal){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertMeal(meal)
        }
    }

    fun updateMeal(meal: Meal){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateMeal(meal)
        }
    }

    fun deleteMeal(meal: Meal){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteMeal(meal)
        }
    }

    //Day
    fun insertDay(day: Day){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertDay(day)
        }
    }

    fun updateDay(day: Day){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateDay(day)
        }
    }

    fun deleteDay(day: Day){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteDay(day)
        }
    }


}