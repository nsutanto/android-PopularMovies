package com.nsutanto.popularmovies.ui.main
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.nsutanto.popularmovies.data.model.Movie


class MainViewModel : ViewModel() {
    private lateinit var movies: MutableLiveData<List<Movie>>


    fun getMovies(): LiveData<List<Movie>> {
        if (!::movies.isInitialized) {
            movies = MutableLiveData()
            loadMovies()
        }
        return movies
    }

    private fun loadMovies() {

    }
}
