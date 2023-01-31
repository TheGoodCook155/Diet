package com.diet.repository

import android.util.Log
import com.diet.db.DbDao
import com.diet.model.*
import com.diet.model.references.DayWithMeals
import com.diet.model.references.MealWithProducts
import com.diet.model.references.MealsWithProductsCrossRef
import com.diet.model.references.ProductsInMeals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.toCollection
import javax.inject.Inject

//TODO
class DietRepository  @Inject constructor(private val dbDao : DbDao){

    private val TAG = "DietRepository"

     fun getAllDays():  Flow<List<Day>> {

        val data = dbDao.getAllDays()
        Log.d(TAG, "getAllDays: DATA: ${data}")
        return data
    }

     fun getAllProducts():  Flow<List<Product>>{

        val data = dbDao.getAllProducts()
        Log.d(TAG, "getAllProducts: DATA: ${data}")

        return data
    }

     fun getAllMeals(): Flow<List<Meal>> {

        val data = dbDao.getAllMeals()
        Log.d(TAG, "getAllMeals: DATA: ${data}")


        return data
    }

     fun getMealsWithProducts(mealName: String): Flow<List<MealWithProducts>> {

        val data = dbDao.getMealsWithProducts(mealName)
        Log.d(TAG, "getMealsWithProducts: DATA: ${data}")


        return data
    }

    fun getProductsInMeals(productName: String): Flow<List<ProductsInMeals>> {

        val data = dbDao.getProductsWithMeal(productName)
        Log.d(TAG, "getMealsWithProducts: DATA: ${data}")


        return data
    }

     fun getDayWithMeals(day: String, date: String) : Flow<List<DayWithMeals>>{

        val data = dbDao.getDayWithMeals(day,date)
        Log.d(TAG, "getDayWithMeals: DATA: ${data}")


        return data
    }

    fun getDayWithMealsNoParam() : Flow<List<DayWithMeals>>{

        val data = dbDao.getDayWithMealsNoParam()
        Log.d(TAG, "getDayWithMeals: DATA: ${data}")


        return data
    }


    //product
    suspend fun insertProduct(product: Product) = dbDao.insertProduct(product)
    suspend fun updateProduct(product: Product) = dbDao.updateProduct(product)
    suspend fun deleteProduct(product: Product) = dbDao.deleteProduct(product)

    //cross ref
    suspend fun insertProductWithMeals(crossRef: MealsWithProductsCrossRef) = dbDao.insertMealsWithProductsCrossRef(crossRef)
    suspend fun updateProductWithMeals(crossRef: MealsWithProductsCrossRef) = dbDao.updateMealsWithProductsCrossRef(crossRef)
    suspend fun deleteProductWithMeals(crossRef: MealsWithProductsCrossRef) = dbDao.deleteMealsWithProductsCrossRef(crossRef)

    //meal
    suspend fun insertMeal(meal: Meal) = dbDao.insertMeal(meal)
    suspend fun updateMeal(meal: Meal) = dbDao.updateMeal(meal)
    suspend fun deleteMeal(meal: Meal) = dbDao.deleteMeal(meal)

    //day
    suspend fun insertDay(day: Day) = dbDao.insertDay(day)
    suspend fun updateDay(day: Day) = dbDao.updateDay(day)
    suspend fun deleteDay(day: Day) = dbDao.deleteDay(day)





}