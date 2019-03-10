package com.nsutanto.popularmovies.ui.movie_detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import com.nsutanto.popularmovies.utils.AppConstants
import com.nsutanto.popularmovies.viewmodel.MovieDetailViewModel
import com.nsutanto.popularmovies.viewmodel.MovieDetailViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import com.nsutanto.popularmovies.data.model.*


class MovieDetailActivity : BaseActivity(),
                            VideoAdapter.IMovieDetailListener,
                            CastAdapter.ICastListener,
                            MovieDetailContract.View {

    @Inject
    lateinit var presenter: MovieDetailContract.Presenter

    @Inject
    lateinit var factory: MovieDetailViewModelFactory

    private lateinit var videoAdapter: VideoAdapter
    private lateinit var castAdapter: CastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        createAdapters()
        setRecyclerView()
        setViewModel()
    }

    // View Methods
    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun showMovieDetail(movie: Movie) {
        tv_overview.text = movie.overview

        val posterPath = movie.posterPath
        if (posterPath != "") Picasso.get()
            .load(AppConstants.BASE_URL_POSTER_BIG + posterPath)
            .into(iv_poster)
    }

    override fun showVideos(videosResponse: MovieVideosResponse) {
        videoAdapter.setVideos(videosResponse.results)
    }

    override fun showCredits(creditResponse: CreditResponse) {
        castAdapter.setCasts(creditResponse.cast)
    }

    override fun getViewModel(): MovieDetailViewModel {
        return ViewModelProviders.of(this, factory).get(MovieDetailViewModel::class.java)
    }

    // Video Listener
    override fun onVideoClicked(video: Video) {
        val url = video.getYouTubeURL()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onCastClicked(cast: Cast) {

    }

    private fun setRecyclerView() {
        val videoManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_videos.adapter = videoAdapter
        rv_videos.layoutManager = videoManager

        val castManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        rv_cast.adapter = castAdapter
        rv_cast.layoutManager = castManager
    }

    private fun createAdapters() {
        videoAdapter = VideoAdapter(this)
        castAdapter = CastAdapter(this)
    }

    private fun setViewModel() {

        val vm = getViewModel()
        vm.videos.observe(this, Observer<MovieVideosResponse> {
                videos -> presenter.onUpdatedVideos(videos)
        })

        vm.credits.observe(this, Observer<CreditResponse> {
                credits -> presenter.onUpdatedCredits(credits)
        })
    }
}