package com.example.mobilecomputingca.data

import android.content.Context
import androidx.room.*

@Database(entities = [FilmEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConvertor::class)
abstract class AppDatabase: RoomDatabase() {

    //Register Data Access Object
    abstract fun filmDao(): FilmDao?

    //Initialise Database
    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase?{
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "FilmsDatabase.db"
                    ).build()

                }
            }

            return INSTANCE
        }
    }
}