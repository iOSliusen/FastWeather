package com.example.fastweather.logic

import androidx.lifecycle.liveData
import com.example.fastweather.logic.model.Place
import com.example.fastweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {

    fun searchPlaces(query:String) = liveData(Dispatchers.IO){
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok"){
                val  places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("---------"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result as Result<List<Place>>)
    }

}