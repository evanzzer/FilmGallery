package com.leafy.filmgallery.ui.home.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leafy.filmgallery.R
import com.leafy.filmgallery.data.source.local.entity.DetailEntity
import com.leafy.filmgallery.databinding.ItemMoviesBinding

class FavoriteAdapter internal constructor() : PagedListAdapter<DetailEntity, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {
    private var callback: OnRequestDetailActivity? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailEntity>() {
            override fun areItemsTheSame(oldItem: DetailEntity, newItem: DetailEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DetailEntity, newItem: DetailEntity): Boolean =
                oldItem == newItem
        }
    }

    fun setCallback(callback: OnRequestDetailActivity) {
        this.callback = callback
    }

    inner class FavoriteViewHolder(private val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(movie: DetailEntity) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(ItemMoviesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val detail = getItem(position)
        if (detail != null) holder.onBind(detail)
    }

    interface OnRequestDetailActivity {
        fun onItemClicked(id: Int)
    }

    fun getSwipedData(swipedPosition: Int): DetailEntity? = getItem(swipedPosition)
}