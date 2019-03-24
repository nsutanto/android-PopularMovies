package com.nsutanto.popularmovies.ui.main.tv

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
import com.nsutanto.popularmovies.data.model.TV
import com.nsutanto.popularmovies.data.model.TVResponse
import com.nsutanto.popularmovies.ui.base.view.BaseFragment
import com.nsutanto.popularmovies.utils.AppConstants.INVALID_ACTIVITY
import com.nsutanto.popularmovies.viewmodel.MainViewModel
import com.nsutanto.popularmovies.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.layout_popular_tv_list.*
import kotlinx.android.synthetic.main.layout_top_rated_tv_list.*
import javax.inject.Inject

class TVFragment : BaseFragment(), TVContract.View {

    interface ITVListener {
        fun onTVClicked(tv: TV)
    }

    @Inject
    lateinit var presenter: TVPresenter

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var popularTVAdapter: TVAdapter
    private lateinit var topRatedTVAdapter: TVAdapter

    private lateinit var tvListener: ITVListener

    override fun getViewModel(): MainViewModel {
        return activity?.run {
            ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        } ?: throw Exception(INVALID_ACTIVITY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
        createAdapters()

        presenter.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        tvListener = context as ITVListener
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.stop()
    }

    override fun showPopularTV(tvResponse: TVResponse) {
        popularTVAdapter.setTVs(tvResponse.results)
        pb_popular_tv.visibility = View.GONE
    }

    override fun showTopRatedTV(tvResponse: TVResponse) {
        topRatedTVAdapter.setTVs(tvResponse.results)
        pb_top_rated_tv.visibility = View.GONE
    }

    private fun setRecyclerView() {
        val vmPopularTV = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_popular_tv.adapter = popularTVAdapter
        rv_popular_tv.layoutManager = vmPopularTV

        rv_popular_tv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollHorizontally(1)) {
                    presenter.fetchPopularTV()
                }
            }
        })

        val vmTopRatedTV = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rv_top_rated_tv.adapter = topRatedTVAdapter
        rv_top_rated_tv.layoutManager = vmTopRatedTV

        rv_top_rated_tv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollHorizontally(1)) {
                    presenter.fetchTopRatedTV()
                }
            }
        })
    }

    private fun createAdapters() {
        popularTVAdapter = TVAdapter(tvListener)
        topRatedTVAdapter = TVAdapter(tvListener)
    }

    private fun setViewModel() {
        mainViewModel = getViewModel()

        mainViewModel.popularTV.observe(this, Observer<TVResponse> {
                tvs -> presenter.onUpdatedPopularTV(tvs)
        })

        mainViewModel.topRatedTV.observe(this, Observer<TVResponse> {
                tvs -> presenter.onUpdatedTopRatedTV(tvs)
        })
    }
}