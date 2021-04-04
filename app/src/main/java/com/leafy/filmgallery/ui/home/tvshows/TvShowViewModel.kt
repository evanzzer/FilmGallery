package com.leafy.filmgallery.ui.home.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.data.source.local.entity.TvShowEntity
import com.leafy.filmgallery.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository): ViewModel() {
    private lateinit var tvShow: LiveData<Resource<PagedList<TvShowEntity>>>

    fun getTvShowList() : LiveData<Resource<PagedList<TvShowEntity>>> = tvShow

    fun fetchTvShowList(table: String, sort: String) {
        tvShow = movieRepository.getTvShows(table, sort)
    }
}