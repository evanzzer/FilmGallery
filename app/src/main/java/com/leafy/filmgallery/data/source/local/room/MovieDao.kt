package com.leafy.filmgallery.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.leafy.filmgallery.data.MovieType
import com.leafy.filmgallery.data.source.local.entity.DetailEntity
import com.leafy.filmgallery.data.source.local.entity.MovieEntity
import com.leafy.filmgallery.data.source.local.entity.TvShowEntity

@Dao
interface MovieDao {
    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Query("SELECT * from DetailEntity where id = :id and type = ${MovieType.MOVIE_TYPE} limit 1")
    fun getMovieDetail(id: Int): LiveData<DetailEntity>

    @Query("SELECT * from DetailEntity where id = :id and type = ${MovieType.TV_SHOW_TYPE} limit 1")
    fun getTvShowDetail(id: Int): LiveData<DetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(detail: DetailEntity)

    @Update
    fun updateDetails(detail: DetailEntity)

    @Query("SELECT * from DetailEntity where type = ${MovieType.MOVIE_TYPE} and favorite = 1")
    fun getMovieFavorites(): DataSource.Factory<Int, DetailEntity>

    @Query("SELECT * from DetailEntity where type = ${MovieType.TV_SHOW_TYPE} and favorite = 1")
    fun getTvShowFavorites(): DataSource.Factory<Int, DetailEntity>
}