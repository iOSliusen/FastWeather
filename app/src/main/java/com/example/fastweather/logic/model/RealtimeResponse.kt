package com.example.fastweather.logic.model

import com.google.gson.annotations.SerializedName

class RealtimeResponse(val status : String,val result:Result)


{

    //类定义到了内部,防止冲突

    class Result(val  realtime:Realtime)

    class Realtime(val skycon:String,val temperature: Float, @SerializedName("air_quality") val airQuality: AirQuality)

    class AirQuality(val aqi: AQI)

    class AQI(val chn: Float)

}