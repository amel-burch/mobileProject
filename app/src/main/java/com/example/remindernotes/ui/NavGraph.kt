package com.example.remindernotes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.remindernotes.ui.screens.TaskDetailScreen
import com.example.remindernotes.ui.screens.TaskListScreen
import com.example.remindernotes.ui.screens.ProfileScreen
import com.example.remindernotes.viewmodel.TaskViewModel

sealed class Screen(val route: String) {
    object TaskList : Screen("task_list")
    object TaskDetail : Screen("task_detail")
    object Calendar : Screen("calendar")
    object Profile : Screen("profile")
}

@Composable
fun NavGraph(navController: NavHostController, taskViewModel: TaskViewModel) {
    NavHost(navController = navController, startDestination = Screen.TaskList.route) {
        composable(Screen.TaskList.route) {
            TaskListScreen(navController, taskViewModel)
        }
        composable(Screen.TaskDetail.route) {
            TaskDetailScreen(navController, taskViewModel)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
    }
}