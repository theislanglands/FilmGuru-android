package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.myfirstapp.database.Movie;
import com.example.myfirstapp.database.MovieDatabase;

class MovieDetails : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieId = intent.getIntExtra("MovieId",0);

        Log.i("transfer", movieId.toString())
    }
}