package com.example.remindernotes.ui.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.remindernotes.data.Task
import com.example.remindernotes.ui.Screen
import com.example.remindernotes.utils.toCustomString
import com.example.remindernotes.viewmodel.TaskViewModel
import java.time.LocalDate
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import com.example.remindernotes.ui.theme.ReminderNotesTheme
import androidx.compose.runtime.MutableState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun TaskListScreen(navController: NavController, taskViewModel: TaskViewModel, isDarkTheme: MutableState<Boolean>) {
    ReminderNotesTheme(darkTheme = isDarkTheme.value){
        Scaffold(
            topBar = { TopAppBar(title = { Text("Tasks") }) },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navController.navigate("task_detail")
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
                }
            },
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            LazyColumn(
                //columns = GridCells.Fixed(2),
                modifier = Modifier.padding(innerPadding)
            ) {
                items(taskViewModel.tasks) { task ->
                    TaskItem(task = task)
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .padding(horizontal = 8.dp)
            .clickable { }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = task.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "",
                    modifier = Modifier.scale(1.2F))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = task.description, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(32.dp))
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(text = task.dueDate.toCustomString(), fontSize = 18.sp, fontWeight = FontWeight.Light)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = task.dueTime.format(DateTimeFormatter.ofPattern("HH:mm")), fontSize = 18.sp)
            }
        }
    }
}

@Preview
@Composable
fun TaskPreview(){
    TaskItem(task = Task(0, "Title1", "Description text", LocalDate.now(), LocalTime.now()))
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Calendar,
        BottomNavItem.Home,
        BottomNavItem.Profile
    )

    BottomAppBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEachIndexed { index, item ->
            if (index == 1) {
                Spacer(Modifier.weight(1f, true))
            }
            IconButton(
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier.padding(horizontal = 35.dp) // Add padding here
            ) {
                Icon(imageVector = item.icon, contentDescription = item.title)
            }
            if (index == 1) {
                Spacer(Modifier.weight(1f, true))
            }
        }
    }
}

sealed class BottomNavItem(val title: String, val icon: ImageVector, val route: String) {
    object Home : BottomNavItem("Home", Icons.Filled.Home, Screen.Home.route)
    object Calendar : BottomNavItem("Calendar", Icons.Filled.DateRange, Screen.TaskList.route)
    object Profile : BottomNavItem("Profile", Icons.Filled.Person, Screen.Profile.route)
}