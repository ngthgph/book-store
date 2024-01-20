package com.example.gbook

import android.app.Application
import com.example.gbook.data.AppContainer
import com.example.gbook.data.DefaultAppContainer

class GBookApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}