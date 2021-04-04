package com.leafy.filmgallery.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leafy.filmgallery.data.source.local.entity.DetailEntity
import com.leafy.filmgallery.data.source.local.entity.MovieEntity
import com.leafy.filmgallery.data.source.local.entity.TvShowEntity

@Database(
    entities = [MovieEntity::class, TvShowEntity::class, DetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            instance ?: synchronized(this) {
                instance = instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "Movies.db"
                ).build()
                return instance as MovieDatabase
            }
    }
}