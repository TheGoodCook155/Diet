package com.diet.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.diet.model.Meal
import com.diet.model.Product
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)//instrumented test
@SmallTest
class DbDaoTest {

 private lateinit var database: DietDatabase
 private lateinit var dao: DbDao

 @Before
 fun setup(){

     database = Room.inMemoryDatabaseBuilder(
         ApplicationProvider.getApplicationContext(),
         DietDatabase::class.java
     ).allowMainThreadQueries().build()
     dao = database.dietDao()

 }

    @After
    fun tearDow(){
        database.close()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    suspend fun insertProduct(){

//         val product = Product(1,"Grav",200,2)
//        val product1 = Product(2,"Kolbasi",500,2);
//        val meal = Meal(2,"Grav so kolbasi",1);
//        dao.insertProduct(product)
//        dao.insertMeal(meal);
//
//        val meals = dao.getProductsInMeals().collect()
//        println(meals)

    }




}