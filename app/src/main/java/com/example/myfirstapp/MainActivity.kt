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

// const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"


class MainActivity : AppCompatActivity() {

    // lateinit = declare variable without instantiation
    lateinit var database : MovieDatabase;
    private lateinit var movieAdapter : MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        System.out.println("onCrete");
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // generate list of 4 movie data objects
        val movies = createListOfMovies();

        // add to database
        initDatabase(movies);
        Log.i("database", "init getting #2" + database.movieDao().loadByID(2).name);

        // recycler view
        var recyclerView: RecyclerView = findViewById(R.id.movieView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(database.movieDao().getAll() as ArrayList<Movie>)
        recyclerView.adapter = movieAdapter
        movieAdapter.onItemClick = {
            // sending data to MovieDetails
            val intent = Intent(this, MovieDetails::class.java)
            intent.putExtra("MovieId", it.id);
            startActivity(intent);
        }
    }


    fun initDatabase(movies: List<Movie>){
        // get singleton instance of database
        database = MovieDatabase.getAppDatabase(this)!!

        // & populate if empty
        Log.i("database", "isEmpty? " + database.movieDao().getAll().isEmpty())
        if (database.movieDao().getAll().isEmpty()) {
            for (movie in movies) {
                Log.i("database", "adding " + movie.name)
                database.movieDao().insert(movie)
            }
        }
    }

    fun createListOfMovies() : ArrayList<Movie> {
        val movies = ArrayList<Movie>()
        // generate 4 movies
        var movie1 = Movie(1,"Pinnocchio", 2022, "https://image.tmdb.org/t/p/original/g8sclIV4gj1TZqUpnL82hKOTK3B.jpg", "A wooden puppet embarks on a thrilling adventure to become a real boy.")
        var movie2 = Movie(2,"Bullet Train", 2022, "https://image.tmdb.org/t/p/original/tVxDe01Zy3kZqaZRNiXFGDICdZk.jpg", "Unlucky assassin Ladybug is determined to do his job peacefully after one too many gigs gone off the rails. Fate, however, may have other plans, as Ladybug's latest mission puts him on a collision course with lethal adversaries from around the globe—all with connected, yet conflicting, objectives—on the world's fastest train.")
        var movie3 = Movie(3,"Samaritan", 2022, "https://image.tmdb.org/t/p/original/vwq5iboxYoaSpOmEQrhq9tHicq7.jpg", "Thirteen year old Sam Cleary  suspects that his mysteriously reclusive neighbor Mr. Smith is actually the legendary vigilante Samaritan, who was reported dead 20 years ago. With crime on the rise and the city on the brink of chaos, Sam makes it his mission to coax his neighbor out of hiding to save the city from ruin.")
        var movie4 = Movie(4,"Lightyear", 2022, "https://image.tmdb.org/t/p/original/65WFr1ZMAbEniIh4jEhbRG9OHHN.jpg", "Legendary Space Ranger Buzz Lightyear embarks on an intergalactic adventure alongside a group of ambitious recruits and his robot companion Sox")

        // adding to list
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);

        return movies;
    }
/*
    fun sendMessage(view: View){
        // respond to button
        val editText = findViewById<EditText>(R.id.editTextTextPersonName2);
        val message = editText.text.toString();
        Log.i("message", message);
        val intent = Intent(this, DisplayMessageActivity::class.java).apply { putExtra(EXTRA_MESSAGE, message)}
        startActivity(intent);
    }
*/

    // FUNCTIONS TO HOOK INTO LIFECYCLE
    override fun onStart() {
        super.onStart()
        Log.i("Test", "STARTED")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Test", "RESUMED")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Test","PAUSE");
    }

    override fun onStop() {
        super.onStop()
        Log.i("Test", "STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Test", "DESTROY")
    }
/*
    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
        // what happends when a user click the item
    }

 */

}

/* on press in recycler view?
in adapter

inner class listViewHolder

override fun onClick(v: view) {
}
if (adapterPosition) = recyclerPosisiotn.NP-POSITOIN {
listener.onItemClick (}
 */