package com.example.plantapp.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobilecomputingca.model.Favourite



@Database(entities = [Favourite::class], version = 1, exportSchema = false)

abstract class AppDatabase: RoomDatabase() {

    // this will be instantiated and all the abstract methods in the DAO will be implemented
    abstract fun favouriteDao(): FavouriteDao?

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "favourites.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}