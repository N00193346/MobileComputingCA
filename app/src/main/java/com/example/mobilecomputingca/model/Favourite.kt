package com.example.mobilecomputingca.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class Favourite(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String
)