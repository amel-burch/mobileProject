package com.example.remindernotes.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remindernotes.data.Task
import com.example.remindernotes.data.TaskDao
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao): ViewModel(){
    val tasks = mutableStateListOf<Task>()

    init {
        viewModelScope.launch {
            tasks.addAll(taskDao.getAll())
        }
    }

    fun addTask(task: Task){

        viewModelScope.launch {
            taskDao.insertTask(task)
            tasks.add(task)
        }
    }
    fun editTask(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task)
            val index = tasks.indexOfFirst { it.id == task.id }
            if (index != -1) {
                tasks[index] = task
            }
        }
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
            tasks.remove(task)
        }
    }
}