package com.example.assistantforsingleparents.Telas.Search
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchScreen(navController: Any?, tasks: Any, onSearch: (Any) -> Unit) {
    val viewModel: SearchViewModel = viewModel()
    val filteredTasks by viewModel.filteredTasks.observeAsState(emptyList())

    LaunchedEffect(tasks) {
        viewModel.initialize(tasks)
    }

    SearchScreen(
        navController,
        tasks = filteredTasks,
        onSearch = { query -> viewModel.search(query.toString()) }
    )

    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = query,
            onValueChange = { query = it }, // Update the query state when the text changes
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Handle search action with the current query
            performSearch(query)
        }) {
            Text("Search")
        }
    }
}

fun performSearch(query: String) {
    // Implement your search logic here
    println("Searching for: $query")
}

