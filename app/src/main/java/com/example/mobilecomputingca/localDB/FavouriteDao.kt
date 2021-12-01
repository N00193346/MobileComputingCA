package com.example.plantapp.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobilecomputingca.model.Favourite
import com.example.mobilecomputingca.model.Film
import com.example.mobilecomputingca.model.Results


@Dao
interface FavouriteDao {

    // this insert deals with creating by inserting and also updates by replacing them
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(favourite: Favourite)

    // favourites is defined in FavouriteEntity as the table name
    @Query("SELECT * FROM favourites WHERE id = :id")
    // Use ? as the object may be null - i.e. now entry in the DB for that id.
    fun getFavouriteById(id: Int): Favourite?

    @Query("SELECT * FROM favourites")
    // Use ? as the object may be null - i.e. now entry in the DB for that id.
    fun getAllFavourite(): List<Film>?

    @Query("DELETE FROM favourites WHERE id = :id")
    // Use ? as the object may be null - i.e. now entry in the DB for that id.
    fun removeFavourite(id: Int)
}