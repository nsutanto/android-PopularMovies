package com.nsutanto.popularmovies.ui.all_item

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import com.nsutanto.popularmovies.ui.movie_detail.MovieDetailActivity
import com.nsutanto.popularmovies.utils.AppConstants
import com.nsutanto.popularmovies.utils.AppConstants.POPULAR_MOVIE_LIST_INTENT
import kotlinx.android.synthetic.main.activity_all.*
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class AllItemActivity : BaseActivity(),
                        AllItemContract.View,
                        AllItemAdapter.IAllItemListener {

    @Inject
    lateinit var presenter: AllItemContract.Presenter

    private lateinit var allItemAdapter: AllItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all)

        val items = intent.getSerializableExtra(POPULAR_MOVIE_LIST_INTENT) as List<Movie>

        createAdapters()
        setRecyclerView()
        allItemAdapter.setItems(items)
    }

    // View Methods
    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onMovieClicked(movie: Movie) {
        startActivity<MovieDetailActivity>(AppConstants.MOVIE_INTENT to movie)
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
    }

    private fun createAdapters() {
        allItemAdapter = AllItemAdapter(this)
    }
}