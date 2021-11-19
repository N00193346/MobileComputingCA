package com.example.mobilecomputingca.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: FilmEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(films: List<FilmEntity>)

    @Query("SELECT * FROM films ORDER BY title ASC")
    fun getAll(): LiveData<List<FilmEntity>>

    @Query("SELECT * FROM films WHERE id = :id")
    fun getFilmById(id: Int):FilmEntity?
}