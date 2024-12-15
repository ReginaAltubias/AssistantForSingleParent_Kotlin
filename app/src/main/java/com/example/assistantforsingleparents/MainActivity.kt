package com.example.assistantforsingleparents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assistantforsingleparents.Telas.Details.TaskDetailsScreen
import com.example.assistantforsingleparents.Telas.Home.HomeScreen
import com.example.assistantforsingleparents.Telas.Home.UserTask
import com.example.assistantforsingleparents.Telas.Login.LoginScreen
import com.example.assistantforsingleparents.Telas.Search.SearchScreen
import com.example.assistantforsingleparents.Telas.Splash.SplashScreen
import com.example.assistantforsingleparents.ui.theme.AssistantForSingleParentsTheme

class MainActivity : ComponentActivity() {
    private val tasksList = listOf<UserTask>() // Replace with your actual task list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssistantForSingleParentsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") {
                        SplashScreen(navController = navController)
                    }
                    composable("login") {
                        LoginScreen(navController = navController)
                    }
                    composable("home") {
                        HomeScreen(navController = navController, tasks = tasksList) // Pass the tasks
                    }
                    composable("taskDetails/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
                        val task = getTaskById(taskId) // Retrieve the task
                        TaskDetailsScreen(
                            navController = navController,
                            task = task,
                            onSave = { savedTask ->
                                // Save the task and navigate to "home"
                                navController.popBackStack()
                                navController.navigate("home")
                            }
                        )
                    }
                    composable("search") {
                        SearchScreen(
                            navController = navController, onSearch = { query -> },
                            tasks = TODO()
                        )
                    }
                }
            }
        }
    }

    private fun getTaskById(taskId: Int?): UserTask? {
        return taskId?.let {
            tasksList.find { task -> task.id == it }
        }
    }
}