package com.diet.db

import androidx.room.*
import com.diet.model.*
import com.diet.model.references.DayWithMeals
import com.diet.model.references.MealWithProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface DbDao {

    @Query("SELECT * FROM day")
    fun getAllDays(): Flow<List<Day>>


   @Query("SELECT * FROM product")
    fun getAllProducts():  Flow<List<Product>>


   @Query("SELECT * FROM meal")
    fun getAllMeals():  Flow<List<Meal>>

   @Transaction
   @Query("SELECT * FROM meal WHERE mealName= :mealName")
    fun getMealsWithProducts(mealName: String):  Flow<List<MealWithProducts>>

   @Transaction
   @Query("SELECT * FROM day WHERE dayName= :day")
    fun getDayWithMeals(day: String):  Flow<List<DayWithMeals>>

    @Transaction
    @Query("SELECT * FROM day WHERE dayName= :day AND date =:date")
    fun getDayWithMeals(day: String, date: String):  Flow<List<DayWithMeals>>

    @Transaction
    @Query("SELECT * FROM day")
    fun getDayWithMealsNoParam():  Flow<List<DayWithMeals>>

 //product
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(product: Product)

    @Delete()
    suspend fun deleteProduct(product: Product)


    //meal
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMeal(meal: Meal)

    @Delete()
    suspend fun deleteMeal(meal: Meal)


    //day
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: Day)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDay(day: Day)

    @Delete()
    suspend fun deleteDay(day: Day)



}