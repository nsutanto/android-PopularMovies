package com.nsutanto.popularmovies.ui.main.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.MovieResponse
import com.nsutanto.popularmovies.ui.base.view.BaseFragment
import com.nsutanto.popularmovies.utils.AppConstants.INVALID_ACTIVITY
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import com.nsutanto.popularmovies.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*
import timber.log.Timber
import javax.inject.Inject


class MovieFragment : BaseFragment(), MovieContract.View {

    interface IMovieListener {
        fun onMovieClicked(movie: Movie)
    }

    @Inject
    lateinit var presenter: MoviePresenter

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var topRatedMoviesAdapter: MovieAdapter

    private lateinit var movieListener: IMovieListener

    private var popularMoviePos: Int = 0
    private var topRatedPos: Int = 0

    override fun getViewModel(): MainViewModel {
       return activity?.run {
           ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
       } ?: throw Exception(INVALID_ACTIVITY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
        createAdapters()

        Timber.d("NICK : onStart")
        presenter.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        movieListener = context as IMovieListener
    }

    override fun onStart() {
        super.onStart()
        presenter.start()

        rv_popular_movie.scrollToPosition(popularMoviePos)
        rv_top_rated_movie.scrollToPosition(topRatedPos)
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()

        Timber.d("NICK : onStop")
        //popularMoviePos = rv_popular_movie.positio
        //topRatedPos = rv_top_rated_movie.scrollX
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("RV_POPULAR_MOVIE_POSITION", rv_popular_movie.scrollX)
        outState.putInt("RV_TOP_RATED_MOVIE_POSITION", rv_top_rated_movie.scrollX)

        Timber.d("NICK : Save Instance State : Scroll X = " + rv_popular_movie.scrollX)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val popularMoviePosition = savedInstanceState?.getInt("RV_POPULAR_MOVIE_POSITION")
        val topRatedMoviePosition = savedInstanceState?.getInt("RV_POPULAR_MOVIE_POSITION")

        Timber.d("NICK : Restore Instance State : Scroll X = " + popularMoviePosition)
        popularMoviePosition?.let { rv_popular_movie.scrollToPosition(popularMoviePosition) }
        topRatedMoviePosition?.let {  rv_top_rated_movie.scrollToPosition(topRatedMoviePosition) }
    }

    override fun showPopularMovies(movieResponse: MovieResponse) {
        popularMoviesAdapter.setMovies(movieResponse.results)
        pb_popular_movie.visibility = View.GONE
    }

    override fun showTopRatedMovies(movieResponse: MovieResponse) {
        topRatedMoviesAdapter.setMovies(movieResponse.results)
        pb_top_rated_movie.visibility = View.GONE
    }

    private fun setRecyclerView() {
        val popularMovieLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_popular_movie.adapter = popularMoviesAdapter
        rv_popular_movie.layoutManager = popularMovieLayoutManager

        rv_popular_movie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollHorizontally(1)) {
                    presenter.fetchPopularMovies()
                }
            }
        })

        val topRatedMovieLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_top_rated_movie.adapter = topRatedMoviesAdapter
        rv_top_rated_movie.layoutManager = topRatedMovieLayoutManager

        rv_top_rated_movie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollHorizontally(1)) {
                    presenter.fetchTopRatedMovies()
                }
            }
        })
    }

    private fun createAdapters() {
        popularMoviesAdapter = MovieAdapter(movieListener)
        topRatedMoviesAdapter = MovieAdapter(movieListener)
    }

    private fun setViewModel() {
        mainViewModel = getViewModel()

        mainViewModel.popularMovies.observe(this, Observer<MovieResponse> {
                movies -> presenter.onUpdatedPopularMovies(movies)
        })

        mainViewModel.topRatedMovies.observe(this, Observer<MovieResponse> {
                movies -> presenter.onUpdatedTopRatedMovies(movies)
        })
    }
}