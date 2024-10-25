package net.alexandroid.getrandomtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.alexandroid.getrandomtask.model.Task
import net.alexandroid.getrandomtask.room.TaskDao
import net.alexandroid.getrandomtask.utils.RandomTask

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insert(task)
        }
    }

    // AddTaskDialog
    private val _isAddTaskShown = MutableStateFlow(false)
    val isAddTaskShown: StateFlow<Boolean> = _isAddTaskShown.asStateFlow()

    fun showAddTaskDialog() {
        _isAddTaskShown.value = true
    }

    fun hideAddTaskDialog() {
        _isAddTaskShown.value = false
    }

    // ShowTaskDialog
    private val _isShowTaskShown = MutableStateFlow(false)
    val isShowTaskShown: StateFlow<Boolean> = _isShowTaskShown.asStateFlow()

    fun showShowTaskDialog(tasks: List<Task>) {
        val selectedTask = RandomTask.select(tasks)
        _selectedTask.value = selectedTask
        _isShowTaskShown.value = true
    }

    fun hideShowTaskDialog() {
        _isShowTaskShown.value = false
        _selectedTask.value = null
    }

    // SelectedTask
    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask.asStateFlow()

}