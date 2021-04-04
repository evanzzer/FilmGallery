package com.leafy.filmgallery.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.data.MovieType
import com.leafy.filmgallery.data.source.local.entity.DetailEntity
import com.leafy.filmgallery.utils.SampleDetailData
import com.leafy.filmgallery.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<DetailEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
    }

    @Test
    fun getDetailMovie() {
        val sampleMovie = SampleDetailData.generateSampleMovie()[0]
        val data = Resource.success(sampleMovie)
        val details = MutableLiveData<Resource<DetailEntity>>()
        details.value = data

        `when`(movieRepository.getMovieDetail(sampleMovie.id)).thenReturn(details)
        viewModel.id = sampleMovie.id
        viewModel.type = MovieType.MOVIE_TYPE
        viewModel.fetchDetail()

        val detail = viewModel.getDetail().value?.data
        verify(movieRepository).getMovieDetail(sampleMovie.id)
        assertNotNull(detail)
        assertEquals(detail?.title, sampleMovie.title)
        assertEquals(detail?.date, sampleMovie.date)
        assertEquals(detail?.category, sampleMovie.category)
        assertEquals(detail?.description, sampleMovie.description)
    }

    @Test(expected = Throwable::class)
    fun getNullDetail() {
        viewModel.getDetail() // Initialized Null Object
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun getFailedLoad() {
        val sampleMovie = SampleDetailData.generateSampleMovie()[20]
        val data = Resource.success(sampleMovie)
        val details = MutableLiveData<Resource<DetailEntity>>()
        details.value = data

        `when`(movieRepository.getMovieDetail(sampleMovie.id)).thenReturn(details)
        viewModel.id = sampleMovie.id
        viewModel.type = MovieType.MOVIE_TYPE

        val detail = viewModel.getDetail().value?.data
        verify(movieRepository).getMovieDetail(sampleMovie.id)
        assertNull(detail)
    }
}