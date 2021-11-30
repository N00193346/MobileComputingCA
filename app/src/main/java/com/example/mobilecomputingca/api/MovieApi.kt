package com.example.mobilecomputingca.api

import com.example.mobilecomputingca.model.Results
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {
    //Get Popular/Top rated
    @GET("movie/top_rated?api_key=7065126bc8a956cc7164ba627aae13d0&language=en-US&page=1")
    suspend fun getPopular(): Results

    //Get latest releases
    @GET("movie/upcoming?api_key=7065126bc8a956cc7164ba627aae13d0&language=en-US&page=1")
    suspend fun getLatest(): Results

    //Search
    @GET("search/movie?api_key=7065126bc8a956cc7164ba627aae13d0&")
    suspend fun getSearch(@Query("query")query: String ): Results

}