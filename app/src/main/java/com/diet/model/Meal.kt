package com.diet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class Meal (
    @PrimaryKey(autoGenerate = false)
    val mealName: String,
    val dayName: String
    )