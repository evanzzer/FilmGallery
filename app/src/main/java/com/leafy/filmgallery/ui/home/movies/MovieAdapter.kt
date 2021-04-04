package com.leafy.filmgallery.ui.home.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leafy.filmgallery.R
import com.leafy.filmgallery.data.source.local.entity.MovieEntity
import com.leafy.filmgallery.databinding.ItemMoviesBinding

class MovieAdapter internal constructor() : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    private var callback: OnRequestDetailActivity? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }

    fun setCallback(callback: OnRequestDetailActivity) {
        this.callback = callback
    }

    inner class MovieViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun onBind(movie: MovieEntity) {
            with(itemView) {
                binding.tvTitle.text = movie.title
                binding.tvDate.text = resources.getString(R.string.dateRelease, movie.date)

                val description = "${movie.description.take(70)}..."
                binding.tvDescription.text = description

                Glide.with(this)
                    .load(movie.imageUrl)
                    .apply(RequestOptions.overrideOf(100, 150))
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .error(R.drawable.ic_baseline_broken_image_24)
                    .into(binding.imgPoster)

                setOnClickListener {
                    callback?.onItemClicked(movie.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) holder.onBind(movie)
    }

    interface OnRequestDetailActivity {
        fun onItemClicked(id: Int)
    }
}