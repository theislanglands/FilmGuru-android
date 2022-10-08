package com.example.myfirstapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM Movie WHERE :movie_id = id")
    fun loadByID(movie_id: Int): Movie

    @Insert
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)
}