package com.nsutanto.popularmovies.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nsutanto.popularmovies.data.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = MovieAdapter()
        rv_movie.adapter = viewAdapter
        rv_movie.layoutManager = viewManager
    }
}
