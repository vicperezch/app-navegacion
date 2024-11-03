package com.uvg.app.data.local

import android.content.Context
import androidx.room.Room

object AppDatabaseFactory {

    fun create(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "rickandmorty.db"
        ).build()
    }
}