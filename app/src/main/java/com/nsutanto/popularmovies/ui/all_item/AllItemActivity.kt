package com.nsutanto.popularmovies.ui.all_item

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.data.model.TVResponse
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import com.nsutanto.popularmovies.ui.movie_detail.MovieDetailActivity
import com.nsutanto.popularmovies.utils.AppConstants
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import com.nsutanto.popularmovies.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_all.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class AllItemActivity : BaseActivity(),
                        AllItemContract.View,
                        AllItemAdapter.IAllItemListener {

    @Inject
    lateinit var presenter: AllItemContract.Presenter

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var allItemAdapter: AllItemAdapter

    private lateinit var itemType: AppConstants.AllItemType

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all)

        createAdapters()
        setRecyclerView()
        setViewModel()
        presenter.create()
    }

    // View Methods
    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }

    override fun onMovieClicked(movie: Movie) {
        startActivity<MovieDetailActivity>(AppConstants.MOVIE_INTENT to movie)
    }

    override fun setItems(objects: List<Any>?) {
        allItemAdapter.setItems(objects)
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


    private fun setRecyclerView() {
        val numColumn = calculateNoOfColumns(this)
        val layoutManager = GridLayoutManager(this, numColumn)
        rv_all_items.adapter = allItemAdapter
        rv_all_items.layoutManager = layoutManager

        rv_all_items.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    presenter.fetchData()
                }
            }
        })
    }

    private fun createAdapters() {
        allItemAdapter = AllItemAdapter(this)
    }

    private fun setViewModel() {
        mainViewModel = getViewModel()

        mainViewModel.popularMovies.observe(this, Observer<MovieResponse> {
                movies -> presenter.onUpdatedPopularMovies(movies)
        })

        mainViewModel.topRatedMovies.observe(this, Observer<MovieResponse> {
                movies -> presenter.onUpdatedTopRatedMovies(movies)
        })

        mainViewModel.popularTV.observe(this, Observer<TVResponse> {
                tvs -> presenter.onUpdatedPopularTV(tvs)
        })

        mainViewModel.topRatedTV.observe(this, Observer<TVResponse> {
                tvs -> presenter.onUpdatedTopRatedTV(tvs)
        })
    }
}