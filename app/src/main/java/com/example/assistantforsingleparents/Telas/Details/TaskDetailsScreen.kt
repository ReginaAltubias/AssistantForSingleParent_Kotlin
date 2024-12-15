package com.example.assistantforsingleparents.Telas.Details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.assistantforsingleparents.Telas.Home.UserTask


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    navController: NavController,
    task: UserTask? = null, // Tarefa existente (para edição) ou null (para nova tarefa)
    onSave: (Task) -> Unit // Callback para salvar a tarefa
) {
    val viewModel: TaskDetailsViewModel = viewModel()

    LaunchedEffect(task) {
        viewModel.loadTask(task)
    }

    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }
    var date by remember { mutableStateOf(task?.date ?: "") }
    var time by remember { mutableStateOf(task?.time ?: "") }
    var category by remember { mutableStateOf(task?.category ?: "") }
    var notificationsEnabled by remember { mutableStateOf(task?.notificationsEnabled ?: false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Tarefa") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .padding(paddingValues)
                .padding(10.dp)
                .fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = androidx.compose.ui.Modifier.fillMaxWidth()
            )

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descrição") },
                modifier = androidx.compose.ui.Modifier.fillMaxWidth()
            )

            TextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Data") },
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                placeholder = { Text("Exemplo: 12/12/2024") }
            )

            TextField(
                value = time,
                onValueChange = { time = it },
                label = { Text("Horário") },
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                placeholder = { Text("Exemplo: 14:00") }
            )

            TextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Categoria") },
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                placeholder = { Text("Exemplo: Escola, Saúde, Lazer") }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = androidx.compose.ui.Modifier.fillMaxWidth()
            ) {
                Text("Notificações")
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
            }

            Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))

            Row(
                modifier = androidx.compose.ui.Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (title.isNotBlank() && description.isNotBlank() && date.isNotBlank() && time.isNotBlank()) {
                            onSave(
                                Task(
                                    id = task?.id ?: 0,
                                    title = title,
                                    description = description,
                                    date = date,
                                    time = time,
                                    category = category,
                                    notificationsEnabled = notificationsEnabled
                                )
                            )
                            navController.popBackStack()
                        } else {
                            // Mostra mensagem de erro (opcional)
                        }
                    }
                ) {
                    Text("Salvar")
                }

                OutlinedButton(onClick = { navController.popBackStack() }) {
                    Text("Cancelar")
                }
            }
        }
    }
}

// Modelo de Dados da Tarefa
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val category: String,
    val notificationsEnabled: Boolean
)
