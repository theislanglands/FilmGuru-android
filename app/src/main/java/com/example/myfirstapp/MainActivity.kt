package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.database.Movie;
import com.example.myfirstapp.database.MovieDatabase;
import com.example.myfirstapp.recyclerview.MovieAdapter

// val: Read-only local variables They can be assigned a value only once. (constant)
// var: Variables that can be reassigned

class MainActivity : AppCompatActivity() {

    // lateinit = declare variable without instantiation
    lateinit var database: MovieDatabase;
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = MovieDatabase.getAppDatabase(this)!!

        // generate list of 4 movie data objects and add to database
        Thread { populateDatabase(createListOfMovies()) }.start()

        // recycler view
        var recyclerView: RecyclerView = findViewById(R.id.movieView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // fetching movies and add to recycler view
        Thread {
            movieAdapter = MovieAdapter(database.movieDao().getAll() as ArrayList<Movie>)
            runOnUiThread(Runnable {
                movieAdapter.onItemClick = {
                    // sending data to MovieDetails
                    val intent = Intent(this, MovieDetails::class.java)
                    intent.putExtra("MovieId", it.id);
                    startActivity(intent);
                }
                recyclerView.adapter = movieAdapter
            })
        }.start()
    }

    fun populateDatabase(movies: List<Movie>) {
        // populates database if empty
        Log.i("database", "Database isEmpty? " + database.movieDao().getAll().isEmpty())
        if (database.movieDao().getAll().isEmpty()) {
            for (movie in movies) {
                Log.i("database", "adding " + movie.name)
                database.movieDao().insert(movie)
            }
        }
    }

    fun createListOfMovies(): ArrayList<Movie> {
        val movies = ArrayList<Movie>()
        movies.add(Movie(1,
            "Pinnocchio",
            2022,
            "https://image.tmdb.org/t/p/original/g8sclIV4gj1TZqUpnL82hKOTK3B.jpg",
            "A wooden puppet embarks on a thrilling adventure to become a real boy."))
        movies.add(Movie(2,
            "Bullet Train",
            2022,
            "https://image.tmdb.org/t/p/original/tVxDe01Zy3kZqaZRNiXFGDICdZk.jpg",
            "Unlucky assassin Ladybug is determined to do his job peacefully after one too many gigs gone off the rails. Fate, however, may have other plans, as Ladybug's latest mission puts him on a collision course with lethal adversaries from around the globe—all with connected, yet conflicting, objectives—on the world's fastest train."))
        movies.add(Movie(3,
            "Samaritan",
            2022,
            "https://image.tmdb.org/t/p/original/vwq5iboxYoaSpOmEQrhq9tHicq7.jpg",
            "Thirteen year old Sam Cleary  suspects that his mysteriously reclusive neighbor Mr. Smith is actually the legendary vigilante Samaritan, who was reported dead 20 years ago. With crime on the rise and the city on the brink of chaos, Sam makes it his mission to coax his neighbor out of hiding to save the city from ruin."))
        movies.add(Movie(4,
            "Lightyear",
            2022,
            "https://image.tmdb.org/t/p/original/65WFr1ZMAbEniIh4jEhbRG9OHHN.jpg",
            "Legendary Space Ranger Buzz Lightyear embarks on an intergalactic adventure alongside a group of ambitious recruits and his robot companion Sox"))
        return movies;
    }

    // FUNCTIONS TO HOOK INTO LIFECYCLE
    override fun onStart() {
        super.onStart()
        Log.i("Lifecycle", "STARTED")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle", "RESUMED")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle", "PAUSE");
    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle", "STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle", "DESTROY")
    }
}

