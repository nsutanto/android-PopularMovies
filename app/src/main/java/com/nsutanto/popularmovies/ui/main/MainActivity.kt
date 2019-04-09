package com.nsutanto.popularmovies.ui.main

import android.os.Bundle
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.TV
import com.nsutanto.popularmovies.ui.all_item.AllItemActivity
import com.nsutanto.popularmovies.ui.movie_detail.MovieDetailActivity
import com.nsutanto.popularmovies.ui.main.movie.MovieFragment
import com.nsutanto.popularmovies.ui.main.tv.TVFragment
import com.nsutanto.popularmovies.ui.tv_detail.TVDetailActivity
import com.nsutanto.popularmovies.utils.AppConstants
import com.nsutanto.popularmovies.utils.AppConstants.ALL_ITEM_TYPE_INTENT
import com.nsutanto.popularmovies.utils.AppConstants.MOVIE_INTENT
import com.nsutanto.popularmovies.utils.AppConstants.MOVIE_LIST_INTENT
import com.nsutanto.popularmovies.utils.AppConstants.TV_INTENT
import com.nsutanto.popularmovies.utils.AppConstants.TV_LIST_INTENT
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import com.nsutanto.popularmovies.viewmodel.MainViewModelFactory
import org.jetbrains.anko.startActivity


class MainActivity : BaseActivity(),
                    MainContract.View,
                    MovieFragment.IMovieListener,
                    TVFragment.ITVListener {

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

        // Set View Pager
        setupViewPager()

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

    // Tab
    override fun displayMovieScreen() {
        view_pager.currentItem = NavBar.MOVIE.value
    }

    override fun displayTVScreen() {
        view_pager.currentItem = NavBar.TV.value
    }

    // Movie Screen Listener
    override fun onMovieClicked(movie: Movie) {
        startActivity<MovieDetailActivity>(MOVIE_INTENT to movie)
    }

    // TV Screen Listener
    override fun onTVClicked(tv: TV) {
        startActivity<TVDetailActivity>(TV_INTENT to tv)
    }

    // Btn All Listener
    override fun onAllPopularMovieClicked(movies: List<Movie>) {
        startActivity<AllItemActivity>(MOVIE_LIST_INTENT to movies, ALL_ITEM_TYPE_INTENT to AppConstants.AllItemType.POPULAR_MOVIE)
    }

    override fun onAllTopRatedMovieClicked(movies: List<Movie>) {
        startActivity<AllItemActivity>(MOVIE_LIST_INTENT to movies, ALL_ITEM_TYPE_INTENT to AppConstants.AllItemType.TOP_RATED_MOVIE)
    }

    override fun onAllPopularTVClicked(tvs: List<TV>) {
        startActivity<AllItemActivity>(TV_LIST_INTENT to tvs, ALL_ITEM_TYPE_INTENT to AppConstants.AllItemType.POPULAR_TV)
    }

    override fun onAllTopRatedTVClicked(tvs: List<TV>) {
        startActivity<AllItemActivity>(TV_LIST_INTENT to tvs, ALL_ITEM_TYPE_INTENT to AppConstants.AllItemType.TOP_RATED_TV)
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(movieFragment)
        adapter.addFragment(tvFragment)
        view_pager.adapter = adapter
    }

    private fun navigationItemSelectedListener(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_movie -> presenter.onMovieTabClicked()
            R.id.nav_tv -> presenter.onTVTabClicked()
        }
        return true
    }
}
