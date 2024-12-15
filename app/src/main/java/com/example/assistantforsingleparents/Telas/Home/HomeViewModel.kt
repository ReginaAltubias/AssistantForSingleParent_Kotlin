package com.example.assistantforsingleparents.Telas.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assistantforsingleparents.Telas.Details.Task

class HomeViewModel : ViewModel() {
    // LiveData para observar as tarefas
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    // Função para carregar tarefas
    fun loadTasks() {
        // Aqui você pode buscar dados do banco de dados (Room) ou de outro repositório
        _tasks.value = listOf(
            Task(1, "Levar filho à escola", "Levar às 8h da manhã", "12/12/2024", "08:00", "Escola", true),
            Task(2, "Consulta médica", "Consulta pediátrica às 14h", "12/12/2024", "14:00", "Saúde", true)
        )
    }

    // Função para remover uma tarefa (opcional)
    fun removeTask(taskId: Int) {
        _tasks.value = _tasks.value?.filterNot { it.id == taskId }
    }
}
