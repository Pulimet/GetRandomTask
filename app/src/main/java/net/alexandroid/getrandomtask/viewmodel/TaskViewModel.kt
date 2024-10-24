package net.alexandroid.getrandomtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.alexandroid.getrandomtask.model.Task
import net.alexandroid.getrandomtask.room.TaskDao

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }
}