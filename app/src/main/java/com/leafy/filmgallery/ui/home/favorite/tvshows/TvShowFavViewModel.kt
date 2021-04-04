package com.leafy.filmgallery.ui.home.favorite.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.data.source.local.entity.DetailEntity

class TvShowFavViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private lateinit var tvShow: LiveData<PagedList<DetailEntity>>

    fun getFavoriteList(): LiveData<PagedList<DetailEntity>> = tvShow

    fun fetchFavoriteList() {
        tvShow = movieRepository.getTvShowFavorites()
    }

    fun setFavorite(detail: DetailEntity) {
        val value = !detail.favorite
        movieRepository.setDetails(detail, value)
    }
}