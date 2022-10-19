package com.example.myfirstapp.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.database.Movie;

class MovieAdapter(private val data : ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onItemClick : ((Movie) -> Unit)? = null

    inner class ViewHolder(item : View): RecyclerView.ViewHolder(item){
        val movieName : TextView = item.findViewById(R.id.movieName)
        val movieYear : TextView = item.findViewById(R.id.movieYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_view_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieName.text = data[position].name
        holder.movieYear.text = data[position].year.toString()
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(data[position])
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}