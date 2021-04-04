package com.leafy.filmgallery.ui.home.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.data.source.local.entity.MovieEntity
import com.leafy.filmgallery.data.source.remote.RemoteDataSource
import com.leafy.filmgallery.utils.SortUtils
import com.leafy.filmgallery.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun fetchMovieList() {
        val sampleData = Resource.success(pagedList)
        `when`(sampleData.data?.size).thenReturn(20)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = sampleData

        `when`(movieRepository.getMovies(SortUtils.tblMovie, SortUtils.NAME)).thenReturn(movies)
        viewModel.fetchMovieList(SortUtils.tblMovie, SortUtils.NAME)
        val movie = viewModel.getMovieList().value?.data
        verify(movieRepository).getMovies(SortUtils.tblMovie, SortUtils.NAME)
        assertNotNull(movie)
        assertEquals(20, movie?.size)

        viewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(sampleData)
    }

    @Test
    fun fetchNoMovieList() {
        val blank = Resource.empty(RemoteDataSource.NULL_ERROR, pagedList)
        `when`(blank.data?.size).thenReturn(0)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = blank

        `when`(movieRepository.getMovies(SortUtils.tblMovie, SortUtils.NAME)).thenReturn(movies)
        viewModel.fetchMovieList(SortUtils.tblMovie, SortUtils.NAME)
        val movie = viewModel.getMovieList().value?.data
        verify(movieRepository).getMovies(SortUtils.tblMovie, SortUtils.NAME)
        assertNotNull(movie)
        assertEquals(0, movie?.size)

        viewModel.getMovieList().observeForever(observer)
        verify(observer).onChanged(blank)
    }
}