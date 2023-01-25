package com.diet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "day")
data class Day (
    @PrimaryKey(autoGenerate = false)
    val dayName: String,
    val date: Long,
   ){
}