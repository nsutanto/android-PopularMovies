package com.nsutanto.popularmovies.ui.main

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.content.Context
import android.support.v7.widget.GridLayoutManager


class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var viewAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun displayMovies(movies: List<Movie>) {
        viewAdapter.setMovies(movies)
    }

    private fun setupRecyclerView() {

        val numColumn = calculateNoOfColumns(this)
        viewManager = GridLayoutManager(this, numColumn)
        viewAdapter = MovieAdapter()
        rv_movie.adapter = viewAdapter
        rv_movie.layoutManager = viewManager
    }

    private fun calculateNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val scalingFactor = 200
        var noOfColumns = (dpWidth / scalingFactor).toInt()
        if (noOfColumns < 2)
            noOfColumns = 2
        return noOfColumns
    }
}
