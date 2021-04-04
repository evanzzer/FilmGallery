package com.leafy.filmgallery.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.leafy.filmgallery.data.source.local.entity.DetailEntity
import com.leafy.filmgallery.data.source.local.entity.MovieEntity
import com.leafy.filmgallery.data.source.local.entity.TvShowEntity
import com.leafy.filmgallery.data.source.local.room.MovieDao
import com.leafy.filmgallery.utils.SortUtils

class LocalDataSource private constructor(private val movieDao: MovieDao) {
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource = instance ?: LocalDataSource(movieDao)
    }

    fun getMovies(table: String, sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedQuery(table, sort)
        return movieDao.getMovies(query)
    }

    fun getTvShows(table: String, sort: String): DataSource.Factory<Int, TvShowEntity> {
        val query = SortUtils.getSortedQuery(table, sort)
        return movieDao.getTvShows(query)
    }

    fun getMovieFavorites(): DataSource.Factory<Int, DetailEntity> = movieDao.getMovieFavorites()

    fun getTvShowFavorites(): DataSource.Factory<Int, DetailEntity> = movieDao.getTvShowFavorites()

    fun getMovieDetail(id: Int): LiveData<DetailEntity> = movieDao.getMovieDetail(id)

    fun getTvShowDetail(id: Int): LiveData<DetailEntity> = movieDao.getTvShowDetail(id)

    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<TvShowEntity>) = movieDao.insertTvShows(tvShows)

    fun insertDetail(detail: DetailEntity) = movieDao.insertDetail(detail)

    fun setMovieFavorite(detail: DetailEntity, value: Boolean) {
        detail.favorite = value
        movieDao.updateDetails(detail)
    }
}