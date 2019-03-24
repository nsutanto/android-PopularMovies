package com.nsutanto.popularmovies.ui.movie_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Video
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class VideoAdapter
    constructor(private val videoListener: IMovieDetailListener) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    interface IMovieDetailListener {
        fun onVideoClicked(video: Video)
    }

    private var videos = listOf<Video>()

    inner class VideoViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        val posterView: ImageView = v.iv_poster
        val title: TextView = v.tv_title

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            videoListener.onVideoClicked(videos[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {

        val video = videos[position]
        val thumbnailPath = video.getYouTubeThumbnailURL()
        if (thumbnailPath != "") {
            Picasso.get()
                .load(thumbnailPath)
                .into(holder.posterView)
        }
        holder.title.text = video.type
    }

    override fun getItemCount() = videos.size

    fun setVideos(videos: List<Video>?) {
        videos?.let {
            this.videos = videos
            notifyDataSetChanged()
        }
    }
}