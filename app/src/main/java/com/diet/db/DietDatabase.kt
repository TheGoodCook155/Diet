package com.diet.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diet.model.Meal
import com.diet.model.Product
import com.diet.model.Day
import com.diet.model.references.DayWithMeals
import com.diet.model.references.MealWithProducts
import com.diet.model.references.MealsWithProductsCrossRef

@Database(entities = [Product::class,Meal::class,Day::class, MealsWithProductsCrossRef::class], version = 1, exportSchema = false)
abstract class DietDatabase : RoomDatabase(){

    abstract fun dietDao() : DbDao

}