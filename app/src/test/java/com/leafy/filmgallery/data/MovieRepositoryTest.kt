package com.leafy.filmgallery.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.leafy.filmgallery.data.source.local.LocalDataSource
import com.leafy.filmgallery.data.source.local.entity.DetailEntity
import com.leafy.filmgallery.data.source.local.entity.MovieEntity
import com.leafy.filmgallery.data.source.local.entity.TvShowEntity
import com.leafy.filmgallery.data.source.remote.RemoteDataSource
import com.leafy.filmgallery.utils.*
import com.leafy.filmgallery.vo.Resource
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MovieRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var remote = mock(RemoteDataSource::class.java)
    private var local = mock(LocalDataSource::class.java)
    private var app = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, app)

    private val moviesList = SampleData.generateSampleMovie()
    private val moviesDetail = SampleDetailData.generateSampleMovie()
    private val movieId = moviesList[0].id
    private val tvShowsList = SampleData.generateSampleTvShow()
    private val tvShowsDetail = SampleDetailData.generateSampleTvShow()
    private val tvShowId = tvShowsList[0].id

    @Test
    fun getMovies() {
        @Suppress("UNCHECKED_CAST")
        val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies(SortUtils.tblMovie, SortUtils.NAME)).thenReturn(dataSourceFactory)
        movieRepository.getMovies(SortUtils.tblMovie, SortUtils.NAME)

        val moviesEntity = Resource.success(PagedListUtil.mockPagedList(moviesList))
        verify(local).getMovies(SortUtils.tblMovie, SortUtils.NAME)
        Assert.assertNotNull(moviesEntity)
        Assert.assertEquals(moviesList.size, moviesEntity.data?.size)
    }

    @Test
    fun getMovieDetail() {
        val detail = MutableLiveData<DetailEntity>()
        detail.value = moviesDetail[0]
        `when`(local.getMovieDetail(movieId)).thenReturn(detail)

        val details = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
        verify(local).getMovieDetail(movieId)
        Assert.assertNotNull(details)
        Assert.assertEquals(details.data?.title, moviesDetail[0].title)
    }

    @Test
    fun getMovieFavorite() {
        @Suppress("UNCHECKED_CAST")
        val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailEntity>
        `when`(local.getMovieFavorites()).thenReturn(dataSourceFactory)
        movieRepository.getMovieFavorites()

        val movieEntity = PagedListUtil.mockPagedList(moviesDetail)
        verify(local).getMovieFavorites()
        Assert.assertNotNull(movieEntity)
        Assert.assertEquals(moviesDetail.size, movieEntity.size)
    }

    @Test
    fun getTvShows() {
        @Suppress("UNCHECKED_CAST")
        val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows(SortUtils.tblTvShow, SortUtils.NAME)).thenReturn(dataSourceFactory)
        movieRepository.getTvShows(SortUtils.tblTvShow, SortUtils.NAME)

        val tvShowsEntity = Resource.success(PagedListUtil.mockPagedList(tvShowsList))
        verify(local).getTvShows(SortUtils.tblTvShow, SortUtils.NAME)
        Assert.assertNotNull(tvShowsEntity)
        Assert.assertEquals(tvShowsList.size, tvShowsEntity.data?.size)
    }

    @Test
    fun getTvShowDetail() {
        val detail = MutableLiveData<DetailEntity>()
        detail.value = tvShowsDetail[0]
        `when`(local.getTvShowDetail(tvShowId)).thenReturn(detail)

        val details = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(tvShowId))
        verify(local).getTvShowDetail(tvShowId)
        Assert.assertNotNull(details)
        Assert.assertEquals(details.data?.title, tvShowsDetail[0].title)
    }

    @Test
    fun getTvShowFavorite() {
        @Suppress("UNCHECKED_CAST")
        val dataSourceFactory =
                mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DetailEntity>
        `when`(local.getTvShowFavorites()).thenReturn(dataSourceFactory)
        movieRepository.getTvShowFavorites()

        val tvShowsEntity = PagedListUtil.mockPagedList(tvShowsDetail)
        verify(local).getTvShowFavorites()
        Assert.assertNotNull(tvShowsEntity)
        Assert.assertEquals(tvShowsList.size, tvShowsEntity.size)
    }
}