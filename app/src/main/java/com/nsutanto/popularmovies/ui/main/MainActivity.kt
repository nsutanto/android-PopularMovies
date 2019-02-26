package com.nsutanto.popularmovies.ui.main

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import com.nsutanto.popularmovies.ui.main.movie.MovieFragment
import com.nsutanto.popularmovies.ui.main.tv.TVFragment


class MainActivity : BaseActivity(), MainContract.View {

    private enum class NavBar(val value: Int) {
        MOVIE(0),
        TV(1),
    }

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var movieFragment: MovieFragment
    private lateinit var tvFragment: TVFragment

    private lateinit var viewAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieFragment = MovieFragment()
        tvFragment = TVFragment()
        nav_bar.setOnNavigationItemSelectedListener { navigationItemSelectedListener(it) }

        //setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun displayMovies(movies: List<Movie>) {
        viewAdapter.setMovies(movies)
    }

    override fun displayMovieFragment() {
        setupContent(movieFragment!!, NavBar.MOVIE)
    }

    override fun displayTVFragment() {
        setupContent(tvFragment!!, NavBar.TV)
    }

    private fun setupContent(newContent: Fragment, navItem: NavBar) {
        nav_bar.menu.getItem(navItem.value).isChecked = true

        val current = supportFragmentManager.findFragmentById(R.id.content_frame)
        if (current != newContent) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, newContent)
                .addToBackStack(navItem.name)
                .commit()
        }
    }

    private fun navigationItemSelectedListener(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_movie -> presenter.onMovieTabClicked()
            R.id.nav_tv -> presenter.onTVTabClicked()
        }
        return true
    }

    private fun setupRecyclerView() {

        val numColumn = calculateNoOfColumns(this)
        viewManager = GridLayoutManager(this, numColumn)
        viewAdapter = MovieAdapter()
        //rv_movie.adapter = viewAdapter
        //rv_movie.layoutManager = viewManager
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
