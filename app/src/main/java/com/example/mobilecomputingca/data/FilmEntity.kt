package com.example.mobilecomputingca.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobilecomputingca.NEW_FILM_ID
import java.util.*


@Entity(tableName = "films")
data class FilmEntity (
    @PrimaryKey(autoGenerate = true)
     var id: Int,
     var title: String,
     var description: String,
     var poster: String,
     var releaseDate: Date
         )

{
    constructor(): this(NEW_FILM_ID, "","","",Date())
    constructor(title: String,description: String, poster: String, date: Date): this(NEW_FILM_ID, "","","",Date())
}