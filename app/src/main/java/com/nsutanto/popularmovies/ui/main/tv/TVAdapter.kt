package com.nsutanto.popularmovies.ui.main.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.TV
import com.nsutanto.popularmovies.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*


class TVAdapter
    constructor(private val tvListener: TVFragment.ITVListener): RecyclerView.Adapter<TVAdapter.MovieViewHolder>() {

    private var tvs = mutableListOf<TV>()

    inner class MovieViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        val posterView: ImageView = v.iv_poster
        val title: TextView = v.tv_title

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            tvListener.onTVClicked(tvs[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val tv = tvs[position]
        val posterPath = tv.posterPath
        if (posterPath != "") {
            Picasso.get()
                .load(AppConstants.BASE_URL_POSTER_MEDIUM + posterPath)
                .into(holder.posterView)
        }
        holder.title.text = tv.name
    }

    override fun getItemCount() = tvs.size

    fun setTVs(tvs: List<TV>?) {
        tvs?.let {
            this.tvs.addAll(tvs)
            notifyDataSetChanged()
        }
    }
}