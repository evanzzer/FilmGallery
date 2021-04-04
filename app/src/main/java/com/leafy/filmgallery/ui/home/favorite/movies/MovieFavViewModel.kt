package com.leafy.filmgallery.ui.home.favorite.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.data.source.local.entity.DetailEntity

class MovieFavViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private lateinit var movie: LiveData<PagedList<DetailEntity>>

    fun getFavoriteList(): LiveData<PagedList<DetailEntity>> = movie

    fun fetchFavoriteList() {
        movie = movieRepository.getMovieFavorites()
    }

    fun setFavorite(detail: DetailEntity) {
        val value = !detail.favorite
        movieRepository.setDetails(detail, value)
    }
}