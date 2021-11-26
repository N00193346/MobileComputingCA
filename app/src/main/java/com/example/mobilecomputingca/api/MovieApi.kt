package com.example.mobilecomputingca.api

import com.example.mobilecomputingca.data.FilmEntity
import com.example.mobilecomputingca.model.Film
import com.example.mobilecomputingca.model.Results
import retrofit2.http.GET



interface MovieApi {
    @GET("now_playing?api_key=7065126bc8a956cc7164ba627aae13d0&language=en-US&page=1")
    suspend fun getPopular(): Results
}