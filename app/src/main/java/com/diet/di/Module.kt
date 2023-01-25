package com.diet.di

import android.content.Context
import androidx.room.Room
import com.diet.db.DbDao
import com.diet.db.DietDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Module {

    @Singleton
    @Provides
    fun provideDietDao(dietDatabase: DietDatabase) : DbDao = dietDatabase.dietDao()

    @Singleton
    @Provides
    fun provideDietDatabase(@ApplicationContext context : Context) : DietDatabase = Room.databaseBuilder(
        context,
        DietDatabase :: class.java,
        "diet_db").fallbackToDestructiveMigration().build()



}