package com.example.remindernotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.remindernotes.data.DatabaseProvider
import com.example.remindernotes.ui.NavGraph
import com.example.remindernotes.ui.theme.ReminderNotesTheme
import com.example.remindernotes.viewmodel.TaskViewModel
import com.example.remindernotes.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val taskDao = DatabaseProvider.getDatabase(this).taskDao()
        val taskViewModel = ViewModelProvider(this, TaskViewModelFactory(taskDao)).get(TaskViewModel::class.java)


        setContent { ReminderNotesTheme {
            Surface(color=MaterialTheme.colorScheme.background) {
                val navController = rememberNavController()
                NavGraph(navController=navController, taskViewModel=taskViewModel)

            }


        }
        }
    }
}


