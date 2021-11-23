package com.example.mobilecomputingca.api

import com.example.mobilecomputingca.data.FilmEntity
import com.example.mobilecomputingca.model.Film
import retrofit2.http.GET

interface MovieApi {
    @GET("films")
    suspend fun getFilms(): List<FilmEntity>
}