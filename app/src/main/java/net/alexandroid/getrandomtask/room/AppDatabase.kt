package net.alexandroid.getrandomtask.room

import androidx.room.Database
import androidx.room.RoomDatabase
import net.alexandroid.getrandomtask.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}