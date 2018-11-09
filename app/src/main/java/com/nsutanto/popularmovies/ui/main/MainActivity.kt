package com.nsutanto.popularmovies.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecyclerView()
        //val vm = ViewModelProviders.of(this).get(MainViewModel::class.java)
        //vm.getUsers().observe(this, Observer<List<User>>{ users ->
            // update UI
        //})
    }

    private fun setupRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = MovieAdapter()
        rv_movie.adapter = viewAdapter
        rv_movie.layoutManager = viewManager
    }
}
