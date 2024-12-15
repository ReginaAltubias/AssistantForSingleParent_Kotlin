package com.example.assistantforsingleparents.Telas.Search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assistantforsingleparents.Telas.Details.Task

class SearchViewModel : ViewModel() {
    private val _allTasks = MutableLiveData<List<Task>>()
    private val _filteredTasks = MutableLiveData<List<Task>>()

    val filteredTasks: LiveData<List<Task>> get() = _filteredTasks

    // Inicializa com todas as tarefas
    fun initialize(tasks: Any) {
        _allTasks.value = tasks
        _filteredTasks.value = tasks
    }

    // Filtra tarefas pelo título ou descrição
    fun search(query: String) {
        _filteredTasks.value = _allTasks.value?.filter {
            it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
        }
    }
}
