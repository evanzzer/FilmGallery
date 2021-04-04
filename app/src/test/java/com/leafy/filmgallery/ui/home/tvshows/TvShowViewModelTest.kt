package com.leafy.filmgallery.ui.home.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.data.source.local.entity.TvShowEntity
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun fetchTvShowList() {
        val sampleData = Resource.success(pagedList)
        `when`(sampleData.data?.size).thenReturn(20)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = sampleData

        `when`(movieRepository.getTvShows(SortUtils.tblTvShow, SortUtils.NAME)).thenReturn(tvShows)
        viewModel.fetchTvShowList(SortUtils.tblTvShow, SortUtils.NAME)
        val tvShow = viewModel.getTvShowList().value?.data
        verify(movieRepository).getTvShows(SortUtils.tblTvShow, SortUtils.NAME)
        assertNotNull(tvShow)
        assertEquals(20, tvShow?.size)

        viewModel.getTvShowList().observeForever(observer)
        verify(observer).onChanged(sampleData)
    }

    @Test
    fun fetchNoTvShowList() {
        val blank = Resource.empty(RemoteDataSource.NULL_ERROR, pagedList)
        `when`(blank.data?.size).thenReturn(0)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = blank

        `when`(movieRepository.getTvShows(SortUtils.tblTvShow, SortUtils.NAME)).thenReturn(tvShows)
        viewModel.fetchTvShowList(SortUtils.tblTvShow, SortUtils.NAME)
        val tvShow = viewModel.getTvShowList().value?.data
        verify(movieRepository).getTvShows(SortUtils.tblTvShow, SortUtils.NAME)
        assertNotNull(tvShow)
        assertEquals(0, tvShow?.size)

        viewModel.getTvShowList().observeForever(observer)
        verify(observer).onChanged(blank)
    }
}