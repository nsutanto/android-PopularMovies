package com.nsutanto.popularmovies.ui.main.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.horizontal_item.view.*


class MovieAdapter
    constructor(private val movieListener: MovieFragment.IMovieListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies = mutableListOf<Movie>()

    inner class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        val posterView: ImageView = v.iv_poster
        val title: TextView = v.tv_title

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            movieListener.onMovieClicked(movies.get(adapterPosition))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movies[position]
        val posterPath = movie.posterPath
        if (posterPath != "") {
            Picasso.get()
                .load(AppConstants.BASE_URL_POSTER_MEDIUM + posterPath)
                .into(holder.posterView)
        }
        holder.title.text = movie.title
    }

    override fun getItemCount() = movies.size

    fun setMovies(movies: List<Movie>?) {
        movies?.let {
            this.movies.addAll(movies)
            notifyDataSetChanged()
        }
    }
}