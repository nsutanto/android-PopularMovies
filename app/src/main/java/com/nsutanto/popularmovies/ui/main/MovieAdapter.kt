package com.nsutanto.popularmovies.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies = listOf<Movie>()

    inner class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        val posterView: ImageView = v.iv_poster

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            //mTaskListener.StartDetailActivity(movies.get(adapterPosition))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val posterPath = movies[position].posterPath
        if (posterPath != "") {
            Picasso.get()
                .load(AppConstants.BASE_URL_POSTER + posterPath)
                .into(holder.posterView)
        }
    }

    override fun getItemCount() = movies.size

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }
}