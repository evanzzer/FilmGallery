package com.leafy.filmgallery.ui.home.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.data.source.local.entity.MovieEntity
import com.leafy.filmgallery.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private lateinit var movie: LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>> = movie

    fun fetchMovieList(table: String, sort: String) {
        movie = movieRepository.getMovies(table, sort)
    }
}