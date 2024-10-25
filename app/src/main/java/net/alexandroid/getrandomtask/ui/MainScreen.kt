package net.alexandroid.getrandomtask.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.alexandroid.getrandomtask.R
import net.alexandroid.getrandomtask.viewmodel.TaskViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: TaskViewModel = koinViewModel()
) {
    val tasks by viewModel.getAllTasks().collectAsState(initial = emptyList())

    Column(modifier = modifier) {
        LazyColumn {
            items(tasks) { task ->
                TaskItem(task)
            }
        }
        Button(
            onClick = {
                viewModel.showShowTaskDialog(tasks)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(stringResource(R.string.wanna_task))
        }
    }

    ShowTaskDialog()

    AddTaskDialog()
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}