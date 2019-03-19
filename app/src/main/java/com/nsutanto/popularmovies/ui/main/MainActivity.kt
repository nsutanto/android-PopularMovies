package com.nsutanto.popularmovies.ui.main

import android.os.Bundle
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.content.Context
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.ui.movie_detail.MovieDetailActivity
import com.nsutanto.popularmovies.ui.main.movie.MovieFragment
import com.nsutanto.popularmovies.ui.main.tv.TVFragment
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import com.nsutanto.popularmovies.viewmodel.MainViewModelFactory
import org.jetbrains.anko.startActivity


class MainActivity : BaseActivity(),
                    MainContract.View,
                    MovieFragment.IMovieListener {

    private enum class NavBar(val value: Int) {
        MOVIE(0),
        TV(1),
    }

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var factory: MainViewModelFactory

    private var movieFragment = MovieFragment()
    private var tvFragment = TVFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // display movie fragment by default
        displayMovieScreen()

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

    override fun displayMovieScreen() {
        setupContent(movieFragment, NavBar.MOVIE)
    }

    override fun displayTVScreen() {
        setupContent(tvFragment, NavBar.TV)
    }

    // Movie Screen Listener
    override fun onMovieClicked(movie: Movie) {
        startActivity<MovieDetailActivity>(MOVIE_INTENT to movie)
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

    companion object {
        const val MOVIE_INTENT = ".movie"
    }
}
