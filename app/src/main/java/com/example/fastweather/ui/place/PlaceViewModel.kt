package com.example.fastweather.ui.place

import android.view.animation.Transformation
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.fastweather.logic.Repository
import com.example.fastweather.logic.model.Place

class PlaceViewModel:ViewModel() {

    private val  searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place> ()

    val placeLiveData = Transformations.switchMap(searchLiveData){ query ->

        Repository.searchPlaces(query)

    }

    fun searchPlace(query:String){
        searchLiveData.value = query
    }
}