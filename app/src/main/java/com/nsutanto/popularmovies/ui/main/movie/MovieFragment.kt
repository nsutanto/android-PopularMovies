package com.nsutanto.popularmovies.ui.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.ui.base.view.BaseFragment
import com.nsutanto.popularmovies.ui.main.MovieAdapter
import com.nsutanto.popularmovies.utils.AppConstants.INVALID_ACTIVITY
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import com.nsutanto.popularmovies.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : BaseFragment(), MovieContract.View {

    @Inject
    lateinit var presenter: MoviePresenter

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var topRatedMoviesAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
        createAdapters()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun showPopularMovies(movieResponse: MovieResponse) {
        popularMoviesAdapter.setMovies(movieResponse.results)
        pb_popular_movie.visibility = View.GONE
    }

    override fun showTopRatedMovies(movieResponse: MovieResponse) {
        topRatedMoviesAdapter.setMovies(movieResponse.results)
        pb_top_rated_movie.visibility = View.GONE
    }

    private fun setRecyclerView() {
        val popularMovieLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_popular_movie.adapter = popularMoviesAdapter
        rv_popular_movie.layoutManager = popularMovieLayoutManager

        val topRatedMovieLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_top_rated_movie.adapter = topRatedMoviesAdapter
        rv_top_rated_movie.layoutManager = topRatedMovieLayoutManager
    }

    private fun createAdapters() {
        popularMoviesAdapter = MovieAdapter()
        topRatedMoviesAdapter = MovieAdapter()
    }

    private fun setViewModel() {
        mainViewModel = activity?.run {
            ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        } ?: throw Exception(INVALID_ACTIVITY)

        mainViewModel.popularMovies.observe(this, Observer<MovieResponse> {
                movies -> presenter.onUpdatedPopularMovies(movies)
        })

        mainViewModel.topRatedMovies.observe(this, Observer<MovieResponse> {
                movies -> presenter.onUpdatedTopRatedMovies(movies)
        })
    }
}