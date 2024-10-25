package net.alexandroid.getrandomtask.utils

import net.alexandroid.getrandomtask.model.Task
import kotlin.random.Random

object RandomTask {
    fun select(tasks: List<Task>): Task? {
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
}