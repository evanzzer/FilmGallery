package com.leafy.filmgallery.ui.home.favorite.tvshows

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
class TvShowFavViewModelTest {
    private lateinit var viewModel: TvShowFavViewModel

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
        viewModel = TvShowFavViewModel(movieRepository)
    }

    @Test
    fun fetchFavoriteList() {
        val sampleData = pagedList
        `when`(sampleData.size).thenReturn(20)
        val tvShowFav = MutableLiveData<PagedList<DetailEntity>>()
        tvShowFav.value = sampleData

        `when`(movieRepository.getTvShowFavorites()).thenReturn(tvShowFav)
        viewModel.fetchFavoriteList()
        val tvShow = viewModel.getFavoriteList().value
        verify(movieRepository).getTvShowFavorites()
        assertNotNull(tvShow)
        assertEquals(20, tvShow?.size)

        viewModel.getFavoriteList().observeForever(observer)
        verify(observer).onChanged(sampleData)
    }

    @Test
    fun fetchNoFavoriteList() {
        val sampleData = pagedList
        `when`(sampleData.size).thenReturn(0)
        val tvShowFav = MutableLiveData<PagedList<DetailEntity>>()
        tvShowFav.value = sampleData

        `when`(movieRepository.getTvShowFavorites()).thenReturn(tvShowFav)
        viewModel.fetchFavoriteList()
        val tvShow = viewModel.getFavoriteList().value
        verify(movieRepository).getTvShowFavorites()
        assertNotNull(tvShow)
        assertEquals(0, tvShow?.size)

        viewModel.getFavoriteList().observeForever(observer)
        verify(observer).onChanged(sampleData)
    }
}