package com.example.mobilecomputingca.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobilecomputingca.NEW_FILM_ID
import com.example.mobilecomputingca.NEW_NOTE_ID
import java.util.*

@Entity(tableName = "notes")
data class FilmEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title: String,
    var description: String,
    var poster: String,
    var releaseDate: Date,

    )
{
    constructor(): this(NEW_FILM_ID,"", "","",Date())
    constructor(title: String, description: String, poster: String,releaseDate: Date,): this(NEW_FILM_ID,title,description,poster,releaseDate)
}