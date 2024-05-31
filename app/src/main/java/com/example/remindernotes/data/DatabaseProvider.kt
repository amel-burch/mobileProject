package com.example.remindernotes.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db : AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (db == null) {
            db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "task_db"
            ).build()
        }
        return db!!
    }
}