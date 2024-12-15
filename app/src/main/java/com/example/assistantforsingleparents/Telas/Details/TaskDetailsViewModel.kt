package com.example.assistantforsingleparents.Telas.Details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskDetailsViewModel : ViewModel() {
    // Campos do formulário
    val title = MutableLiveData("")
    val description = MutableLiveData("")
    val date = MutableLiveData("")
    val time = MutableLiveData("")
    val category = MutableLiveData("")
    val notificationsEnabled = MutableLiveData(false)

    // Função para carregar uma tarefa para edição
    fun loadTask(task: Task?) {
        task?.let {
            title.value = it.title
            description.value = it.description
            date.value = it.date
            time.value = it.time
            category.value = it.category
            notificationsEnabled.value = it.notificationsEnabled
        }
    }

    // Função para salvar uma nova tarefa ou editar existente
    fun saveTask(onSave: (Task) -> Unit) {
        if (!title.value.isNullOrBlank() && !description.value.isNullOrBlank() && !date.value.isNullOrBlank() && !time.value.isNullOrBlank()) {
            val task = Task(
                id = 0, // Use 0 para novas tarefas ou o ID existente para edição
                title = title.value ?: "",
                description = description.value ?: "",
                date = date.value ?: "",
                time = time.value ?: "",
                category = category.value ?: "",
                notificationsEnabled = notificationsEnabled.value ?: false
            )
            onSave(task)
        }
    }
}
