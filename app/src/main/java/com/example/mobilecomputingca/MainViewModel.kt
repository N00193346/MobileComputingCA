package com.example.mobilecomputingca

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilecomputingca.api.RetrofitInstance
import com.example.mobilecomputingca.data.FilmEntity
import com.example.mobilecomputingca.data.SampleDataProvider
import com.example.mobilecomputingca.model.Film
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _films: MutableLiveData<List<Film>> = MutableLiveData()

    //Using mutable live data so it can be changed at run time
    val films: MutableLiveData<List<Film>>

        get() = _films


    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    //When app is initialised
    init {
//        filmsList.value = getFilms()
        getFilms()
    }

    fun getFilms() {
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedFilms = RetrofitInstance.api.getFilms()
            Log.i(TAG, "Got posts: $fetchedFilms")
            _films.value = fetchedFilms.results
            _isLoading.value = false

        }
    }
}