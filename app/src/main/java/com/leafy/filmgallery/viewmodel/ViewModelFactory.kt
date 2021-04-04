package com.leafy.filmgallery.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.di.Injection
import com.leafy.filmgallery.ui.detail.DetailViewModel
import com.leafy.filmgallery.ui.home.favorite.movies.MovieFavViewModel
import com.leafy.filmgallery.ui.home.favorite.tvshows.TvShowFavViewModel
import com.leafy.filmgallery.ui.home.movies.MovieViewModel
import com.leafy.filmgallery.ui.home.tvshows.TvShowViewModel

class ViewModelFactory private constructor(private val mMovieRepository: MovieRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) ->
                MovieViewModel(mMovieRepository) as T

            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel(mMovieRepository) as T

            modelClass.isAssignableFrom(TvShowViewModel::class.java) ->
                TvShowViewModel(mMovieRepository) as T

            modelClass.isAssignableFrom(MovieFavViewModel::class.java) ->
                MovieFavViewModel(mMovieRepository) as T

            modelClass.isAssignableFrom(TvShowFavViewModel::class.java) ->
                TvShowFavViewModel(mMovieRepository) as T

            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}