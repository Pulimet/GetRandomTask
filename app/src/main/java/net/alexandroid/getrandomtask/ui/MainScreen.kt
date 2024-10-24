package net.alexandroid.getrandomtask.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import net.alexandroid.getrandomtask.model.Task
import net.alexandroid.getrandomtask.room.AppDatabase
import net.alexandroid.getrandomtask.viewmodel.TaskViewModel
import net.alexandroid.getrandomtask.viewmodel.TaskViewModelFactory
import kotlin.random.Random


@Composable
fun MainScreen(modifier: Modifier = Modifier, showDialogClick: Boolean) {
    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "task-database"
        ).build()
    }
    val taskDao = db.taskDao()
    val tasks by taskDao.getAllTasks().collectAsState(initial = emptyList())

    val viewModel: TaskViewModel = viewModel(factory = TaskViewModelFactory(taskDao))

    var showAddTaskDialog by remember { mutableStateOf(false) }
    var showTaskDialog by remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    LaunchedEffect(showDialogClick) {
        if (showDialogClick) {
            showAddTaskDialog = true
        }
    }

    Column(modifier = modifier) {
        LazyColumn {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
        Button(
            onClick = {
                selectedTask = selectRandomTask(tasks)
                showTaskDialog = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Хочу задание!")
        }
    }

    ShowTaskDialog(selectedTask, showTaskDialog) {
        showTaskDialog = it
    }

    AddTaskDialog(showAddTaskDialog) { show, newTask ->
        showAddTaskDialog = show
        newTask?.let {
            viewModel.addTask(it)
        }
    }
}

fun selectRandomTask(tasks: List<Task>): Task? {
    if (tasks.isEmpty()) return null

    val totalWeight = tasks.sumOf { it.weight }
    val randomNumber = Random.nextInt(totalWeight)

    var currentWeight = 0
    for (task in tasks) {
        currentWeight += task.weight
        if (randomNumber <= currentWeight) {
            return task
        }
    }

    return null
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(showDialogClick = false)
}