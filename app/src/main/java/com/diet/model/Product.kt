package com.diet.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "product")
class Product (
    @PrimaryKey(autoGenerate = false)
    val productName : String,
    val individualCalories: Int,
    val mealName: String

    ) {
    override fun toString(): String {
        return "Product(productName='$productName', individualCalories=$individualCalories, mealName='$mealName')"
    }
}
