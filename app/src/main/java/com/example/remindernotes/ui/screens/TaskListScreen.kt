package com.example.remindernotes.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.remindernotes.data.Task
import com.example.remindernotes.ui.Screen
import com.example.remindernotes.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun TaskListScreen(navController: NavController, taskViewModel: TaskViewModel) {
    Scaffold(
        topBar= {
            TopAppBar(title = { Text("Tasks") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.TaskDetail.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { LazyColumn (modifier=Modifier.padding(16.dp)){
        items(taskViewModel.tasks) { task ->
            TaskItem(task = task)
        }
    }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card (modifier= Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
        .clickable { }
    ){
        Column (modifier= Modifier.padding(16.dp)){
            Text(text = task.title)
            Text(text = task.description)
        }
    }
}
