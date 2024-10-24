package net.alexandroid.getrandomtask.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import net.alexandroid.getrandomtask.room.AppDatabase
import net.alexandroid.getrandomtask.viewmodel.TaskViewModel
import net.alexandroid.getrandomtask.viewmodel.TaskViewModelFactory


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

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(showDialogClick) {
        if (showDialogClick) {
            showDialog = true
        }
    }

    Column(modifier = modifier) {
        LazyColumn {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
    }
    AddTaskDialog(showDialog) { show, newTask ->
        showDialog = show
        newTask?.let {
            viewModel.addTask(it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(showDialogClick = false)
}