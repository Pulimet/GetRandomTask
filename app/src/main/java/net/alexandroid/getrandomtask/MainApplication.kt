package net.alexandroid.getrandomtask

import android.app.Application
import net.alexandroid.getrandomtask.koin.Koin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Koin.init(this)
    }
}

