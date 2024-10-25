package net.alexandroid.getrandomtask.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import net.alexandroid.getrandomtask.R
import net.alexandroid.getrandomtask.viewmodel.TaskViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ShowTaskDialog(viewModel: TaskViewModel = koinViewModel()) {
    val isShowTaskShown by viewModel.isShowTaskShown.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()

    if (isShowTaskShown && selectedTask != null) {
        AlertDialog(
            onDismissRequest = { viewModel.hideShowTaskDialog() },
            title = { Text(stringResource(R.string.your_task_is)) },
            text = { TaskItem(task = selectedTask!!) },
            confirmButton = {
                Button(onClick = { viewModel.hideShowTaskDialog() }) {
                    Text(stringResource(R.string.btn_accept))
                }
            },
            dismissButton = {
                Button(onClick = { viewModel.hideShowTaskDialog() }) {
                    Text(stringResource(R.string.btn_deny))
                }
            }
        )
    }
}