package com.example.mobilecomputingca.data

import java.util.*


class SampleDataProvider {

    companion object {
        private val sampleTitle1 = "Dune"
        private val sampleTitle2 = "Avengers"
        private val sampleTitle3 = "BladeRunner"

        private val sampleDescription1 = "This is a film"
        private val sampleDescription2 = "This is a film"
        private val sampleDescription3 = "This is a film"

        private val samplePoster1 = "https://source.unsplash.com/random"
        private val samplePoster2 = "https://source.unsplash.com/random"
        private val samplePoster3 = "https://source.unsplash.com/random"


        private fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

        fun getFilms() = arrayListOf(
            FilmEntity(1, sampleTitle1, sampleDescription1, samplePoster1 ),
            FilmEntity(2, sampleTitle2, sampleDescription2, samplePoster2 ),
            FilmEntity(3, sampleTitle3, sampleDescription3, samplePoster3 )
        )
    }
}
