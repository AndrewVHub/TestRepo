package com.example.testrepo

import android.app.Application
import com.example.testrepo.di.serviceModule
import com.example.testrepo.di.usersModule
import com.example.testrepo.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(serviceModule, viewmodelModule, usersModule))
        }
    }
}