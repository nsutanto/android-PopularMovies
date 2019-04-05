package com.nsutanto.popularmovies.ui.movie_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Cast
import com.nsutanto.popularmovies.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_horizontal.view.*

class CastAdapter
    constructor(private val castListener: ICastListener) : RecyclerView.Adapter<CastAdapter.CreditViewHolder>() {

    interface ICastListener {
        fun onCastClicked(cast: Cast)
    }

    private var casts = listOf<Cast>()

    inner class CreditViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        val posterView: ImageView = v.iv_poster
        val title: TextView = v.tv_title

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            castListener.onCastClicked(casts[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CreditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_horizontal, parent, false)
        return CreditViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {

        val cast = casts[position]
        val profilePath = cast.profilePath
        if (profilePath != "") {
            Picasso.get()
                .load(AppConstants.BASE_URL_POSTER + profilePath)
                .into(holder.posterView)
        }
        holder.title.text = cast.name
    }

    override fun getItemCount() = casts.size

    fun setCasts(casts: List<Cast>?) {
        casts?.let {
            this.casts = casts
            notifyDataSetChanged()
        }
    }
}