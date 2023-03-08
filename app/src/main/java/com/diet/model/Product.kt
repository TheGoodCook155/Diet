package com.diet.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "product")
data class Product (
    @PrimaryKey(autoGenerate = false)
    var productName : String,
    var individualCalories: Int,
//    var mealName: String

    ) {


}
