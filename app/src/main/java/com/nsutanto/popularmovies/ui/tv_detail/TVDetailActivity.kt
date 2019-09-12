package com.nsutanto.popularmovies.ui.tv_detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.ui.base.view.BaseActivity
import com.nsutanto.popularmovies.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import com.nsutanto.popularmovies.data.model.*
import com.nsutanto.popularmovies.viewmodel.TVDetailViewModel
import com.nsutanto.popularmovies.viewmodel.TVDetailViewModelFactory

class TVDetailActivity : BaseActivity(),
                            VideoAdapter.IMovieDetailListener,
                            CastAdapter.ICastListener,
                            TVDetailContract.View {

    @Inject
    lateinit var presenter: TVDetailContract.Presenter

    @Inject
    lateinit var factory: TVDetailViewModelFactory

    private lateinit var videoAdapter: VideoAdapter
    private lateinit var castAdapter: CastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_detail)

        createAdapters()
        setRecyclerView()
        setViewModel()

        setSupportActionBar(toolbar_title)
    }

    // View Methods
    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun showTVDetail(tv: TV) {
        tv_overview.text = tv.overview

        val backdropPath = tv.backdropPath
        if (backdropPath != "") Picasso.get()
            .load(AppConstants.BASE_URL_POSTER_BIG + backdropPath)
            .into(app_bar_image)

        val posterPath = tv.posterPath
        if (posterPath != "") Picasso.get()
            .load(AppConstants.BASE_URL_POSTER_BIG + posterPath)
            .into(iv_poster)

        collapse_toolbar_layout.title = tv.name

        //tv_release_date.text = tv.
        tv_rating.text = tv.voteAverage
    }

    override fun showVideos(videosResponse: MovieVideosResponse) {
        videoAdapter.setVideos(videosResponse.results)
    }

    override fun showCredits(creditResponse: CreditResponse) {
        castAdapter.setCasts(creditResponse.cast)
    }

    override fun getViewModel(): TVDetailViewModel {
        return ViewModelProviders.of(this, factory).get(TVDetailViewModel::class.java)
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