package net.alexandroid.getrandomtask.utils

import net.alexandroid.getrandomtask.model.Task
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.random.Random

class RandomTaskTest {

    @Test
    fun `select function returns all tasks at least once when 3 tasks with weight 1 are provided`() {
        val tasks = listOf(
            Task(name = "Task 1", color = 0, weight = 1),
            Task(name = "Task 2", color = 0, weight = 1),
            Task(name = "Task 3", color = 0, weight = 1),
        )

        val results = mutableSetOf<Task>()
        repeat(100) {
            val selectedTask = RandomTask.select(tasks)
            selectedTask?.let { results.add(it) }
        }

        assertTrue(results.containsAll(tasks))
    }

    @Test
    fun `select function returns all 10 tasks at least once which provided with a random weights`() {
        val tasks = (1..10).map { index ->
            Task(name = "Task $index", color = 0, weight = Random.nextInt(1, 6))
        }

        val unselectedTasks = tasks.toMutableSet()
        var iterations = 0

        while (unselectedTasks.isNotEmpty() && iterations < 1000) {
            val selectedTask = RandomTask.select(tasks)
            selectedTask?.let { unselectedTasks.remove(it) }
            iterations++
        }

        assertTrue(unselectedTasks.isEmpty())
    }
}