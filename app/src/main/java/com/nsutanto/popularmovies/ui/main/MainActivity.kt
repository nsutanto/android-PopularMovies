package com.nsutanto.popularmovies.ui.main

import android.app.Activity
import android.os.Bundle
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.content.Context
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.ui.main.movie.MovieFragment
import com.nsutanto.popularmovies.ui.main.tv.TVFragment
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import com.nsutanto.popularmovies.viewmodel.ViewModelFactory


class MainActivity : BaseActivity(), MainContract.View {

    private enum class NavBar(val value: Int) {
        MOVIE(0),
        TV(1),
    }

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var factory: ViewModelFactory

    private var movieFragment = MovieFragment()
    private var tvFragment = TVFragment()
    private lateinit var mainActivityListener: MainActivityListener

    private lateinit var viewAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // display movie fragment by default
        displayMovieFragment()

        // setup nav bar
        nav_bar.setOnNavigationItemSelectedListener { navigationItemSelectedListener(it) }
    }

    // View Methods
    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun getActivity(): Activity {
        return this
    }

    override fun displayMovies(movies: List<Movie>) {
        viewAdapter.setMovies(movies)
    }

    override fun displayMovieFragment() {
        setupContent(movieFragment, NavBar.MOVIE)
    }

    override fun displayTVFragment() {
        setupContent(tvFragment, NavBar.TV)
    }

    override fun displayPopularMovies(movies: List<Movie>?) {
        movieFragment.updatePopularMovies(movies)
    }

    override fun displayTopRatedMovies(movies: List<Movie>?) {
        movieFragment.updateTopRatedMovies(movies)
    }

    override fun displayPopularTV(movies: List<Movie>?) {
        tvFragment.updatePopularTV(movies)
    }

    override fun displayTopRatedTV(movies: List<Movie>?) {
        tvFragment.updateTopRatedTV(movies)
    }

    // Private Methods
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

    private fun getCurrentFragment() : Fragment? {
        return supportFragmentManager.findFragmentById(R.id.content_frame)
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
