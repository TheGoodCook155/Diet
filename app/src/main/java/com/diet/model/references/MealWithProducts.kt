package com.diet.model.references

import androidx.room.Embedded
import androidx.room.Relation
import com.diet.model.Meal
import com.diet.model.Product

data class MealWithProducts (
    @Embedded
    val meal: Meal,
    @Relation(
        parentColumn = "mealName",
        entityColumn = "mealName"
    )
    val products: List<Product>
        )