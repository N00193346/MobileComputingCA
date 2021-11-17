package com.example.mobilecomputingca

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobilecomputingca.data.FilmEntity
import com.example.mobilecomputingca.data.SampleDataProvider

class MainViewModel : ViewModel() {

    //Using mutable live data so it can be changed at run time
    val filmsList = MutableLiveData<List<FilmEntity>>()

    //When app is initialised
    init {
        filmsList.value = SampleDataProvider.getFilms()
    }
}