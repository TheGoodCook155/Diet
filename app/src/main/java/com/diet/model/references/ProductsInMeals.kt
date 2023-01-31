package com.diet.model.references

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.diet.model.Meal
import com.diet.model.Product

data class ProductsInMeals(
    @Embedded
    val product: Product,
    @Relation(
        parentColumn = "productName",
        entityColumn = "mealName",
        associateBy = Junction(MealsWithProductsCrossRef::class)
    )
    val meals: List<Meal>
)
