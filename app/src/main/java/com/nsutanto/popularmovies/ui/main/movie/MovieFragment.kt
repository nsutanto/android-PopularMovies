package com.nsutanto.popularmovies.ui.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.ui.base.view.BaseFragment
import com.nsutanto.popularmovies.ui.main.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : BaseFragment(), MovieContract.View {

    @Inject
    lateinit var presenter: MoviePresenter

    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPopularMoviesRV()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    fun updatePopularMovies(movies: List<Movie>) {
        popularMoviesAdapter.setMovies(movies)
    }

    private fun setPopularMoviesRV() {
        viewManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        popularMoviesAdapter = MovieAdapter()
        rv_popular_movie.adapter = popularMoviesAdapter
        rv_popular_movie.layoutManager = viewManager
    }
}