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
    val _currentFavourite: MutableLiveData<Favourite> = MutableLiveData()
    private val _watchList: MutableLiveData<List<Film>> = MutableLiveData()

    
    val currentFavourite : LiveData<Favourite>
    get() = _currentFavourite

    val watchList: MutableLiveData<List<Film>>
    get() = _watchList



    fun getFavourite(favouriteId: Int) {
        Log.i(TAG, "Id : " + favouriteId)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val favourite =
                    database?.favouriteDao()?.getFavouriteById(favouriteId)

                favourite?.let {
                    _currentFavourite.postValue(it)
                    Log.i(TAG, "MyNotes Returned from DB" + it.title)
                }
            }
        }
    }

//    init {
//        getWatchList()
//    }


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

    fun saveFavourite(favouriteEntity: Favourite) {

        viewModelScope.launch {

            withContext(Dispatchers.IO){
                database?.favouriteDao()?.insertFavourite(favouriteEntity)


            }
        }
    }

    fun removeFavourite(id: Int) {

        viewModelScope.launch {

            withContext(Dispatchers.IO){
                database?.favouriteDao()?.removeFavourite(id)
            }
        }
    }


}