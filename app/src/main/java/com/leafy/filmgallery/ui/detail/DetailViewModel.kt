package com.leafy.filmgallery.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.data.MovieType
import com.leafy.filmgallery.data.source.local.entity.DetailEntity
import com.leafy.filmgallery.vo.Resource

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    // getTitle() and setTitle(title: String) already build-in Kotlin with this defined variable
    var id = 0
    var type = 0

    lateinit var details: LiveData<Resource<DetailEntity>>

    fun fetchDetail() {
        details = when (type) {
            MovieType.MOVIE_TYPE -> movieRepository.getMovieDetail(id)
            MovieType.TV_SHOW_TYPE -> movieRepository.getTvShowDetail(id)
            else -> throw Throwable("Unknown Type: $type")
        }
    }

    fun getDetail() : LiveData<Resource<DetailEntity>> = details

    fun setFavorite() {
        val detail = details.value?.data
        if (detail != null) {
            val value = !detail.favorite
            movieRepository.setDetails(detail, value)
        }
    }
}