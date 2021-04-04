package com.leafy.filmgallery.ui.home.favorite.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.data.source.local.entity.DetailEntity
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
class MovieFavViewModelTest {
    private lateinit var viewModel: MovieFavViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<DetailEntity>>

    @Mock
    private lateinit var pagedList: PagedList<DetailEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavViewModel(movieRepository)
    }

    @Test
    fun fetchFavoriteList() {
        val sampleData = pagedList
        `when`(sampleData.size).thenReturn(20)
        val movieFav = MutableLiveData<PagedList<DetailEntity>>()
        movieFav.value = sampleData

        `when`(movieRepository.getMovieFavorites()).thenReturn(movieFav)
        viewModel.fetchFavoriteList()
        val tvShow = viewModel.getFavoriteList().value
        verify(movieRepository).getMovieFavorites()
        assertNotNull(tvShow)
        assertEquals(20, tvShow?.size)

        viewModel.getFavoriteList().observeForever(observer)
        verify(observer).onChanged(sampleData)
    }

    @Test
    fun fetchNoFavoriteList() {
        val sampleData = pagedList
        `when`(sampleData.size).thenReturn(0)
        val movieFav = MutableLiveData<PagedList<DetailEntity>>()
        movieFav.value = sampleData

        `when`(movieRepository.getMovieFavorites()).thenReturn(movieFav)
        viewModel.fetchFavoriteList()
        val tvShow = viewModel.getFavoriteList().value
        verify(movieRepository).getMovieFavorites()
        assertNotNull(tvShow)
        assertEquals(0, tvShow?.size)

        viewModel.getFavoriteList().observeForever(observer)
        verify(observer).onChanged(sampleData)
    }
}