package com.nsutanto.popularmovies.ui.detail_movie

import android.os.Bundle
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import com.nsutanto.popularmovies.viewmodel.ViewModelFactory
import javax.inject.Inject

class DetailMovieActivity : BaseActivity(), DetailMovieContract.View {

    @Inject
    lateinit var presenter: DetailMovieContract.Presenter

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    // View Methods
    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    /*
    override fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
    }
    */
}