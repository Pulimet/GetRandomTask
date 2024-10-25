package net.alexandroid.getrandomtask.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.alexandroid.getrandomtask.R
import net.alexandroid.getrandomtask.model.Task
import net.alexandroid.getrandomtask.viewmodel.TaskViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Content(modifier)
    ShowTaskDialog()
    AddTaskDialog()
}

@Composable
private fun Content(modifier: Modifier = Modifier, viewModel: TaskViewModel = koinViewModel()) {
    val tasks by viewModel.getAllTasks().collectAsState(initial = emptyList())

    Box(modifier = modifier.fillMaxSize()) {
        TasksList(tasks)
        WannaTaskButton(viewModel, tasks)
    }
}

@Composable
private fun TasksList(tasks: List<Task>) {
    LazyColumn(modifier = Modifier.padding(bottom = 60.dp)) {
        items(tasks) { task ->
            TaskItem(task)
        }
    }
}

@Composable
private fun BoxScope.WannaTaskButton(viewModel: TaskViewModel, tasks: List<Task>) {
    Button(
        onClick = {
            viewModel.showShowTaskDialog(tasks)
        },
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(vertical = 16.dp, horizontal = 80.dp)
            .fillMaxWidth()
    ) {
        Text(stringResource(R.string.wanna_task))
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}