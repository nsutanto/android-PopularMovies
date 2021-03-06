package com.nsutanto.popularmovies.ui.all_item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.data.model.TV
import com.nsutanto.popularmovies.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_horizontal.view.*


class AllItemAdapter
constructor(private val allItemListener: IAllItemListener) : RecyclerView.Adapter<AllItemAdapter.ItemViewHolder>() {

    interface IAllItemListener {
        fun onMovieClicked(movie: Movie)
        fun onTVClicked(tv: TV)
    }

    private data class ItemData(val path: String, val title: String)

    private var objects = mutableListOf<Any>()

    inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        val posterView: ImageView = v.iv_poster
        val title: TextView = v.tv_title

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val movie = objects[adapterPosition] as? Movie
            movie?.let { allItemListener.onMovieClicked(movie)}

            val tv = objects[adapterPosition] as? TV
            tv?.let { allItemListener.onTVClicked(tv)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_all, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = objects[position]
        val itemData = getItemData(item)
        val profilePath = itemData?.path
        if (profilePath != "") {
            Picasso.get()
                .load(AppConstants.BASE_URL_POSTER_MEDIUM + profilePath)
                .into(holder.posterView)
        }
        holder.title.text = itemData?.title
    }

    override fun getItemCount() = objects.size

    private fun getItemData(item: Any): ItemData? {

        val movie = item as? Movie

        val posterPath = movie?.posterPath
        val title = movie?.title
        if (posterPath != null && title != null) {
            return ItemData(posterPath, title)
        }

        val tv = item as? TV
        val tvPosterPath = tv?.posterPath
        val tvName = tv?.name
        if (tvPosterPath != null && tvName != null)
        tv?.let { return ItemData(tvPosterPath, tvName) }

        return null
    }

    fun setItems(objects: List<Any>?) {
        objects?.let {
            this.objects.addAll(objects)
            notifyDataSetChanged()
        }
    }

}