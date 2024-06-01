package com.example.remindernotes.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.remindernotes.data.Task
import com.example.remindernotes.ui.Screen
import com.example.remindernotes.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavController, taskViewModel: TaskViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tasks",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.TaskDetail.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(innerPadding)
        ) {
            items(taskViewModel.tasks) { task ->
                TaskItem(task = task)
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
            Text(text = task.title, style = MaterialTheme.typography.headlineMedium)
            Text(text = task.description, style = MaterialTheme.typography.bodySmall)
        }
    }
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
    object Home : BottomNavItem("Home", Icons.Filled.Home, Screen.TaskList.route)
    object Calendar : BottomNavItem("Calendar", Icons.Filled.DateRange, Screen.TaskDetail.route)
    object Profile : BottomNavItem("Profile", Icons.Filled.Person, Screen.Profile.route)
}