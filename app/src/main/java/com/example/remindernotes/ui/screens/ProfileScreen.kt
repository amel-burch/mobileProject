package com.example.remindernotes.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.remindernotes.ui.theme.ReminderNotesTheme
import androidx.compose.runtime.MutableState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, isDarkTheme: MutableState<Boolean>) {


    ReminderNotesTheme(darkTheme = isDarkTheme.value) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier.weight(1f), // This will make the Surface take up all available space except for the BottomNavigationBar
                color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Profile",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            actions = {
                                IconButton(onClick = { isDarkTheme.value = !isDarkTheme.value }) {
                                    Icon(
                                        imageVector = if (isDarkTheme.value) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
                                        contentDescription = if (isDarkTheme.value) "Switch to light mode" else "Switch to dark mode"
                                    )
                                }
                            }
                        )
                    },
                    content = { // Move BottomNavigationBar inside the content lambda
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "This is some text in the middle of the screenddd")
                        }
                    }
                )
            }
            BottomNavigationBar(navController) // Place BottomNavigationBar outside the Surface
        }
    }
}