package net.alexandroid.getrandomtask.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import net.alexandroid.getrandomtask.R
import net.alexandroid.getrandomtask.model.Task

@Composable
fun ShowTaskDialog(
    selectedTask: Task?,
    showTaskDialog: Boolean,
    setShowTaskDialog: (Boolean) -> Unit
) {
    if (showTaskDialog && selectedTask != null) {
        AlertDialog(
            onDismissRequest = { setShowTaskDialog(false) },
            title = { Text(stringResource(R.string.your_task_is)) },
            text = { TaskItem(task = selectedTask) },
            confirmButton = {
                Button(onClick = { setShowTaskDialog(false) }) {
                    Text(stringResource(R.string.btn_accept))
                }
            },
            dismissButton = {
                Button(onClick = { setShowTaskDialog(false) }) {
                    Text(stringResource(R.string.btn_deny))
                }
            }
        )
    }
}