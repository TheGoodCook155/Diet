package com.diet.model.references

import androidx.room.Entity

@Entity(primaryKeys = ["mealName","productName"])
data class MealsWithProductsCrossRef(
    val mealName: String,
    val productName: String
)
