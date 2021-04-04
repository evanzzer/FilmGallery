package com.leafy.filmgallery.di

import android.content.Context
import com.leafy.filmgallery.data.MovieRepository
import com.leafy.filmgallery.data.source.local.LocalDataSource
import com.leafy.filmgallery.data.source.local.room.MovieDatabase
import com.leafy.filmgallery.data.source.remote.RemoteDataSource
import com.leafy.filmgallery.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(MovieDatabase.getInstance(context).movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}