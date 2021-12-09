package com.example.mobilecomputingca

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mobilecomputingca.model.Favourite
import com.example.mobilecomputingca.model.Film
import com.example.plantapp.localDB.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WatchListViewModel (app: Application) : AndroidViewModel(app) {
    private val _films: MutableLiveData<List<Film>> = MutableLiveData()
    private val database = AppDatabase.getInstance(app)
    val currentFavourite = MutableLiveData<Favourite>()

    //Using mutable live data so it can be changed at run time
    val films: MutableLiveData<List<Film>>

    get() = _films
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    //When fragment is initialised
    init {
        getWatchList()
    }

    //Function to get all films stored in the watchlist (Local database)
    fun getWatchList() {
        Log.i(TAG, "Attempting getting watchlist :")
        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                //User favouriteDAO interface to use getAllFavourites function
                val favourite =
                    database?.favouriteDao()?.getAllFavourite()

                Log.i(TAG, "Watchlist returned from DB\n" + favourite)
                _films.postValue(favourite!!)

            }
        }
    }
}

