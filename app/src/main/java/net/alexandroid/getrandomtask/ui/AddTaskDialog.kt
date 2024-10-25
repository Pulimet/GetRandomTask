package net.alexandroid.getrandomtask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import net.alexandroid.getrandomtask.R
import net.alexandroid.getrandomtask.model.Task
import net.alexandroid.getrandomtask.viewmodel.TaskViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun AddTaskDialog(viewModel: TaskViewModel = koinViewModel()) {
    val isAddTaskShown by viewModel.isAddTaskShown.collectAsState()
    val colors = remember {
        listOf(
            Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan,
            Color.Magenta, Color.Gray, Color.LightGray, Color.DarkGray, Color.Black,
            Color(0xFFF44336), Color(0xFFE91E63), Color(0xFF9C27B0),
            Color(0xFF673AB7), Color(0xFF3F51B5), Color(0xFF2196F3),
            Color(0xFF03A9F4), Color(0xFF00BCD4), Color(0xFF009688),
            Color(0xFF4CAF50)
        )
    }

    if (isAddTaskShown) {
        var taskName by remember { mutableStateOf("") }
        var taskColor by remember { mutableStateOf(colors[0]) }
        var taskWeight by remember { mutableIntStateOf(1) }

        AlertDialog(
            onDismissRequest = { viewModel.hideAddTaskDialog() },
            title = { Text(stringResource(R.string.add_task)) },
            text = {
                Column {
                    TextField(
                        value = taskName,
                        onValueChange = { taskName = it },
                        label = { Text(stringResource(R.string.task_title)) }
                    )

                    LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
                        items(colors) { color ->
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .clickable { taskColor = color }
                                    .padding(4.dp)
                            )
                        }
                    }

                    Slider(
                        value = taskWeight.toFloat(),
                        onValueChange = { taskWeight = it.toInt() },
                        valueRange = 1f..5f,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(stringResource(R.string.weight, taskWeight))
                }
            },
            confirmButton = {
                Button(onClick = {
                    val newTask = Task(
                        name = taskName,
                        color = taskColor.toArgb(),
                        weight = taskWeight
                    )
                    viewModel.hideAddTaskDialog()
                    viewModel.addTask(newTask)
                }) {
                    Text(stringResource(R.string.btn_add))
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.hideAddTaskDialog() }) {
                    Text(stringResource(R.string.btn_cancel))
                }
            }
        )
    }
}