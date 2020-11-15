package com.example.fastweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.fastweather.SunnyWeatherApplication
import com.example.fastweather.logic.model.Place
import com.google.gson.Gson


object PlaceDao {

    fun savePlace(place: Place){
        sharedPreferences().edit{

            putString("place",Gson().toJson(place))

        }
    }


    fun getPlace():Place{
        val place = sharedPreferences().getString("place","")
        return Gson().fromJson(place,Place::class.java)
    }
    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = SunnyWeatherApplication.context.getSharedPreferences("weather",Context.MODE_PRIVATE)

}