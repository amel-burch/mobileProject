package com.example.remindernotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.remindernotes.data.DatabaseProvider
import com.example.remindernotes.ui.NavGraph
import com.example.remindernotes.ui.theme.ReminderNotesTheme
import com.example.remindernotes.viewmodel.TaskViewModel
import com.example.remindernotes.viewmodel.TaskViewModelFactory
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val taskDao = DatabaseProvider.getDatabase(this).taskDao()
        val taskViewModel = ViewModelProvider(this, TaskViewModelFactory(taskDao)).get(TaskViewModel::class.java)

        setContent {
            val isDarkTheme = remember { mutableStateOf(false) }
            MainContent(rememberNavController(), taskViewModel, isDarkTheme)
        }
    }

    @Composable
    fun MainContent(navController: NavHostController, taskViewModel: TaskViewModel, isDarkTheme: MutableState<Boolean>) {
        ReminderNotesTheme(darkTheme = isDarkTheme.value) {
            Surface(color=MaterialTheme.colorScheme.background) {
                NavGraph(navController=navController, taskViewModel=taskViewModel, isDarkTheme = isDarkTheme)
            }
        }
    }
}


