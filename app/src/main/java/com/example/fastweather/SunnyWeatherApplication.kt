package com.example.fastweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication: Application(){

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context

        const val TOKEN = "o1QeAablKB6QH07b"

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


}