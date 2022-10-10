package com.example.myfirstapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "image_url") val movie_url: String,
    @ColumnInfo(name = "plot") val plot: String
)