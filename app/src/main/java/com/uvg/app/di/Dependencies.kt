package com.uvg.app.di

import android.content.Context
import androidx.room.Room
import com.uvg.app.data.local.AppDatabase
import com.uvg.app.data.local.AppDatabaseFactory

object Dependencies {
    private var database: AppDatabase? = null

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: AppDatabaseFactory.create(context).also { database = it }
        }
    }
}