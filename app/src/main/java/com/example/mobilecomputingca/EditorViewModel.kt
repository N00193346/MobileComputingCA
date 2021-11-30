package com.example.mobilecomputingca

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.mobilecomputingca.model.Favourite
import com.example.plantapp.localDB.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditorViewModel (app: Application) : AndroidViewModel(app) {
    private val database = AppDatabase.getInstance(app)
    val currentFavourite = MutableLiveData<Favourite>()

    fun getFavourite(favouriteId: Int) {
        Log.i(TAG, "Id : " + favouriteId)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val favourite =
                    database?.favouriteDao()?.getFavouriteById(favouriteId)

                favourite?.let {
                    currentFavourite.postValue(it)
                    Log.i(TAG, "MyNotes Returned from DB" + it.title)
                }
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


}