package com.example.mobilecomputingca

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.mobilecomputingca.model.Favourite
import com.example.mobilecomputingca.model.Film
import com.example.plantapp.localDB.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditorViewModel (app: Application) : AndroidViewModel(app) {
    private val database = AppDatabase.getInstance(app)
    val _currentFavourite: MutableLiveData<Favourite?> = MutableLiveData()
    private val _watchList: MutableLiveData<List<Film>> = MutableLiveData()

    
    val currentFavourite : MutableLiveData<Favourite?>
    get() = _currentFavourite

    val watchList: MutableLiveData<List<Film>>
    get() = _watchList



        //Get the film from the watchlist to know if the film has been favourited/in the watchlist
    fun getFavourite(favouriteId: Int) {
        Log.i(TAG, "Id : " + favouriteId)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val favourite =
                    database?.favouriteDao()?.getFavouriteById(favouriteId)

                favourite?.let {
                    currentFavourite.postValue(it)
                    Log.i(TAG, "Got film:" + it.title + " from the watchlist")
                }
            }
        }
    }

    //Change the value of current favourite to null, so the fragment knows the film has been removed from the watch list
    fun nullFavourite() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val favourite = null

                favourite?.let {
                    _currentFavourite.postValue(it)
                }

            }
        }
    }

//    init {
//        getWatchList()
//    }

    //Function to get watchlist
    fun getWatchList() {
        Log.i(TAG, "Attempting getting watchlist :")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val favourite =
                    database?.favouriteDao()?.getAllFavourite()
                Log.i("Get watchList-ViewModel", "Watchlist returned from DB\n" + favourite)
                _watchList.postValue(favourite!!)

            }
        }
    }
    //Function to insert a film into the watchlist
    fun saveFavourite(favouriteEntity: Favourite) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                database?.favouriteDao()?.insertFavourite(favouriteEntity)


            }
        }
    }
    //Function to remove a film from the watchlist
    fun removeFavourite(id: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                database?.favouriteDao()?.removeFavourite(id)
                _currentFavourite.postValue(null)
            }
        }
    }


}