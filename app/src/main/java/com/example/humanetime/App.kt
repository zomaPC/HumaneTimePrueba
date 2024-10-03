package com.example.humanetime

import android.app.Application
import com.example.humanetime.di.diModule
import org.koin.core.context.startKoin

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin { modules(diModule) }
    }
}