package com.example.fastweather.ui.place

import android.view.animation.Transformation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.fastweather.logic.Repository
import com.example.fastweather.logic.dao.PlaceDao
import com.example.fastweather.logic.model.Place

class PlaceViewModel:ViewModel() {


    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()

    fun isPlaceSaved () = Repository.isPlaceSaved()


    private val  searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place> ()

    val placeLiveData = Transformations.switchMap(searchLiveData){ query ->

        Repository.searchPlaces(query)

    }

    fun searchPlace(query:String){
        searchLiveData.value = query
    }
}