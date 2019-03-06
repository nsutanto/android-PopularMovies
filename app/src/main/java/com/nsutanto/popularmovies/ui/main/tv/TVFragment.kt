package com.nsutanto.popularmovies.ui.main.tv

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
import kotlinx.android.synthetic.main.fragment_tv.*
import javax.inject.Inject

class TVFragment : BaseFragment(), TVContract.View {

    @Inject
    lateinit var presenter: TVPresenter

    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private var popularTVAdapter: MovieAdapter? = null
    private var topRatedTVAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = activity?.run {
            ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        mainViewModel.popularTV.observe(this, Observer<MovieResponse> {
                movies -> updatePopularTV(movies)
        })

        mainViewModel.topRatedTV.observe(this, Observer<MovieResponse> {
                movies -> updateTopRatedTV(movies)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPopularTVRV()
        setTopRatedTVRV()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun displayPopularTV(movies: List<Movie>?) {
        popularTVAdapter?.setMovies(movies)
    }

    override fun displayTopRatedTV(movies: List<Movie>?) {
        topRatedTVAdapter?.setMovies(movies)
    }

    fun updatePopularTV(movieResponse: MovieResponse) {
        //presenter.onUpdatePopularTV(movies)
        popularTVAdapter?.setMovies(movieResponse.results)
        pb_popular_tv.visibility = View.GONE
    }

    fun updateTopRatedTV(movieResponse: MovieResponse) {
        //presenter.onUpdateTopRatedTV(movies)
        topRatedTVAdapter?.setMovies(movieResponse.results)
        pb_top_rated_tv.visibility = View.GONE
    }

    private fun setPopularTVRV() {
        popularTVAdapter = MovieAdapter()
        val viewManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_popular_tv.adapter = popularTVAdapter
        rv_popular_tv.layoutManager = viewManager
    }

    private fun setTopRatedTVRV() {
        topRatedTVAdapter = MovieAdapter()
        val viewManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_top_rated_tv.adapter = topRatedTVAdapter
        rv_top_rated_tv.layoutManager = viewManager
    }
}