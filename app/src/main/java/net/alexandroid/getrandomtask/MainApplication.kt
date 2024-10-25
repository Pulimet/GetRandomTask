package net.alexandroid.getrandomtask

import android.app.Application
import androidx.room.Room
import net.alexandroid.getrandomtask.room.AppDatabase
import net.alexandroid.getrandomtask.viewmodel.TaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}

val appModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "task-database").build()
    }
    single { get<AppDatabase>().taskDao() }

    viewModelOf(::TaskViewModel)
}