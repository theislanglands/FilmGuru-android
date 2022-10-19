package com.example.myfirstapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase


@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    // singleton instance of movieDB
    companion object {
        private var INSTANCE: MovieDatabase? = null

        fun getAppDatabase(context: Context): MovieDatabase? {
            // create db if doesn't exist
            if (INSTANCE == null) {
                INSTANCE = databaseBuilder(context.applicationContext, MovieDatabase::class.java, "MovieDatabase")
                    .build()
            }
            return INSTANCE
        }
    }
}
