package net.alexandroid.getrandomtask.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.alexandroid.getrandomtask.R
import net.alexandroid.getrandomtask.model.Task
import net.alexandroid.getrandomtask.viewmodel.TaskViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskItem(task: Task, viewModel: TaskViewModel = koinViewModel()) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(Color(task.color))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = task.name)
        Spacer(Modifier.weight(1f))
        Text(text = stringResource(R.string.weight, task.weight), modifier = Modifier.width(80.dp))

        IconButton(onClick = { viewModel.deleteTask(task) }) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    TaskItem(
        task = Task(
            name = stringResource(R.string.example_task),
            color = Color.Red.toArgb(),
            weight = 50
        )
    )
}