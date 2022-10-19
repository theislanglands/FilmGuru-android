package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

import com.example.myfirstapp.database.Movie;
import com.example.myfirstapp.database.MovieDatabase;
import com.squareup.picasso.Picasso

class MovieDetails : AppCompatActivity() {

    lateinit var database: MovieDatabase
    lateinit var selectedMovie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        // getting selected movie ID
        val movieId = intent.getIntExtra("MovieId", 0)
        Log.i("transfer", "movie id recieved from intent " + movieId.toString())

        // get fields for data in movieDetails
        val headerTextView = findViewById<TextView>(R.id.textViewMovieDetailsHeader)
        val plotTextView = findViewById<TextView>(R.id.textViewMovieDetailsPlot)
        val filmPoster = findViewById<ImageView>(R.id.imageViewMoviePoster)

        // Getting selected movie from database and setting fields
        database = MovieDatabase.getAppDatabase(this)!!
        Thread {
            selectedMovie = database.movieDao().loadByID(movieId)
            Log.i("transfer", "movie name: " + database.movieDao().loadByID(movieId))
            runOnUiThread(Runnable {
                headerTextView.text = selectedMovie.name + " " + selectedMovie.year
                plotTextView.text = selectedMovie.plot
                Picasso.get().load(selectedMovie.movie_url).into(filmPoster)
            })
        }.start()
    }
}