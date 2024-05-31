package com.example.remindernotes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<Task>

    @Insert
    suspend fun insertTask(task: Task)
}