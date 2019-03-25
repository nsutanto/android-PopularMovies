package com.nsutanto.popularmovies.ui.all_item

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_all.*
import javax.inject.Inject

class AllItemActivity : BaseActivity(), AllItemContract.View {

    @Inject
    lateinit var presenter: AllItemContract.Presenter

    private lateinit var allItemAdapter: AllItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all)

        createAdapters()
        setRecyclerView()
    }

    // View Methods
    override fun onStart() {
        super.onStart()
        presenter.start()
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
        allItemAdapter = AllItemAdapter()
    }
}