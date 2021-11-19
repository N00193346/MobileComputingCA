package com.example.mobilecomputingca

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mobilecomputingca.data.AppDatabase
import com.example.mobilecomputingca.data.FilmDao
import com.example.mobilecomputingca.data.FilmEntity
import com.example.mobilecomputingca.data.SampleDataProvider
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before


@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao: FilmDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.filmDao()!!
    }
    @Test
    fun createFilms() {
        dao.insertAll(SampleDataProvider.getFilms())
        val count = dao.getCount()
        assertEquals(count, SampleDataProvider.getFilms().size)
    }

    @Test
    fun insertFilm() {
        val film = FilmEntity()
        film.title = "James Bond"
        dao.insertFilm(film)
        val savedFilm = dao.getFilmById(1)
        assertEquals(savedFilm?.id ?: 0, 1)
    }

    @After
    fun closeDb(){
        database.close()
    }
}