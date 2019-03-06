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
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.ui.base.view.BaseFragment
import com.nsutanto.popularmovies.ui.main.MovieAdapter
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
    private var popularMovies: MovieResponse? = null
    private var topRatedMovies: MovieResponse? = null
    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var topRatedMoviesAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = activity?.run {
            ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        mainViewModel.popularMovies.observe(this, Observer<MovieResponse> {
                movies -> updatePopularMovies(movies)
        })

        mainViewModel.topRatedMovies.observe(this, Observer<MovieResponse> {
                movies -> updateTopRatedMovies(movies)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPopularMoviesRV()
        setTopRatedMoviesRV()

        popularMovies?.let { updatePopularMovies(popularMovies!!)}
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
        onSaveInstanceState(Bundle())
    }

    fun updatePopularMovies(movieResponse: MovieResponse) {
        popularMovies = movieResponse
        popularMoviesAdapter.setMovies(popularMovies?.results)
        pb_popular_movie.visibility = View.GONE
    }

    fun updateTopRatedMovies(movieResponse: MovieResponse) {
        topRatedMovies = movieResponse
        topRatedMoviesAdapter.setMovies(topRatedMovies?.results)
        pb_top_rated_movie.visibility = View.GONE
    }

    private fun setPopularMoviesRV() {
        popularMoviesAdapter = MovieAdapter()
        val viewManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_popular_movie.adapter = popularMoviesAdapter
        rv_popular_movie.layoutManager = viewManager
    }

    private fun setTopRatedMoviesRV() {
        topRatedMoviesAdapter = MovieAdapter()
        val viewManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_top_rated_movie.adapter = topRatedMoviesAdapter
        rv_top_rated_movie.layoutManager = viewManager
    }
}