package com.diet.model.references

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.diet.model.Day
import com.diet.model.Meal


data class DayWithMeals (

    @Embedded
    val day: Day,

    @Relation(
        parentColumn = "dayName",
        entityColumn = "dayName")
    val meals: List<Meal>
        )
