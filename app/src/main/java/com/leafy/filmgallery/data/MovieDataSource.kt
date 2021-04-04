package com.leafy.filmgallery.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.leafy.filmgallery.data.source.local.entity.DetailEntity
import com.leafy.filmgallery.data.source.local.entity.MovieEntity
import com.leafy.filmgallery.data.source.local.entity.TvShowEntity
import com.leafy.filmgallery.vo.Resource

interface MovieDataSource {
    fun getMovies(table: String, sort: String): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieDetail(id: Int): LiveData<Resource<DetailEntity>>
    fun getMovieFavorites(): LiveData<PagedList<DetailEntity>>
    fun getTvShows(table: String, sort: String): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShowDetail(id: Int): LiveData<Resource<DetailEntity>>
    fun getTvShowFavorites(): LiveData<PagedList<DetailEntity>>
    fun setDetails(detail: DetailEntity, value: Boolean)
}