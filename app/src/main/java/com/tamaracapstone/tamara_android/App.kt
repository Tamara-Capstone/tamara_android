package com.tamaracapstone.tamara_android

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Locator.initWith(this)
    }
}