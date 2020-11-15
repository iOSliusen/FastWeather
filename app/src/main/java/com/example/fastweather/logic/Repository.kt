package com.example.fastweather.logic

import androidx.lifecycle.liveData
import com.example.fastweather.logic.dao.PlaceDao
import com.example.fastweather.logic.model.Place
import com.example.fastweather.logic.model.Weather
import com.example.fastweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

object Repository {

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getPlace()

    fun isPlaceSaved () = PlaceDao.isPlaceSaved()

    fun searchPlaces(query:String) = fire(Dispatchers.IO){
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok"){
                val  places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("---------"))
            }
        }




    fun refreshWeather(lng:String,lat:String) = fire(Dispatchers.IO){

            coroutineScope {
                val deferredRealtime = async {
                    SunnyWeatherNetwork.getRealtimeWeather(lng,lat)
                }
                val  deferredDaily = async {
                    SunnyWeatherNetwork.getDetailWeather(lng,lat)
                }
                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()
                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                    val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)
                } else {
                   Result.failure(
                       RuntimeException("realtime response status is ${realtimeResponse.status}" +
                               "daily response status is ${dailyResponse.status}")
                   )
                }
            }
       
    }


    private fun <T> fire(context:CoroutineContext,block:suspend  () -> Result<T>) = liveData<Result<T>>(context){

        val result = try{
            block()
        }catch (e:Exception){
            Result.failure<T>(e)
        }
        emit(result)

    }

}