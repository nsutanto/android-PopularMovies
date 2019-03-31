package com.nsutanto.popularmovies.ui.all_item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nsutanto.popularmovies.R
import com.nsutanto.popularmovies.data.model.Movie
import com.nsutanto.popularmovies.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_horizontal.view.*


class AllItemAdapter
constructor(private val allItemListener: IAllItemListener) : RecyclerView.Adapter<AllItemAdapter.ItemViewHolder>() {

    interface IAllItemListener {
        fun onMovieClicked(movie: Movie)
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
            allItemListener.onMovieClicked(objects[adapterPosition] as Movie)
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
                .load(AppConstants.BASE_URL_POSTER + profilePath)
                .into(holder.posterView)
        }
        holder.title.text = itemData?.title
    }

    override fun getItemCount() = objects.size

    private fun getItemData(item: Any): ItemData? {

        val movie = item as? Movie
        movie?.let { return ItemData(movie.posterPath!!, movie.title!!) }

        return null
    }

    fun setItems(objects: List<Any>?) {
        objects?.let {
            this.objects.addAll(objects)
            notifyDataSetChanged()
        }
    }

}