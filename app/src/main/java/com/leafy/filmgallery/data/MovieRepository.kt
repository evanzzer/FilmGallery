package com.leafy.filmgallery.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.leafy.filmgallery.data.source.local.LocalDataSource
import com.leafy.filmgallery.data.source.local.entity.DetailEntity
import com.leafy.filmgallery.data.source.local.entity.MovieEntity
import com.leafy.filmgallery.data.source.local.entity.TvShowEntity
import com.leafy.filmgallery.data.source.remote.ApiResponse
import com.leafy.filmgallery.data.source.remote.RemoteDataSource
import com.leafy.filmgallery.data.source.remote.response.moviedetail.MovieDetailResponse
import com.leafy.filmgallery.data.source.remote.response.movieresponse.MovieResultsItem
import com.leafy.filmgallery.data.source.remote.response.tvshowdetail.TvShowDetailResponse
import com.leafy.filmgallery.data.source.remote.response.tvshowresponse.TvShowResultsItem
import com.leafy.filmgallery.utils.ApiConfig
import com.leafy.filmgallery.utils.AppExecutors
import com.leafy.filmgallery.vo.Resource

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            executors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, executors)
            }
    }

    override fun getMovies(table: String, sort: String): LiveData<Resource<PagedList<MovieEntity>>> =
        object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(table, sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResultsItem>>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MovieResultsItem>) {
                val list = ArrayList<MovieEntity>()
                data.forEach { entity ->
                    val movie = MovieEntity(
                        entity.id,
                        entity.title,
                        ApiConfig.BASE_IMAGE_URL + entity.posterPath,
                        entity.releaseDate,
                        entity.overview
                    )
                    list.add(movie)
                }
                localDataSource.insertMovies(list)
            }
        }.asLiveData()

    override fun getMovieDetail(id: Int): LiveData<Resource<DetailEntity>> =
        object : NetworkBoundResource<DetailEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailEntity> = localDataSource.getMovieDetail(id)

            override fun shouldFetch(data: DetailEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(id)

            override fun saveCallResult(data: MovieDetailResponse) {
                val movie = DetailEntity(
                    data.id,
                    data.title,
                    ApiConfig.BASE_IMAGE_URL + data.posterPath,
                    data.releaseDate,
                    "${data.runtime / 60}h ${data.runtime % 60}m",
                    data.genres.joinToString { it.name },
                    data.overview,
                    MovieType.MOVIE_TYPE
                )
                localDataSource.insertDetail(movie)
            }

        }.asLiveData()

    override fun getMovieFavorites(): LiveData<PagedList<DetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getMovieFavorites(), config).build()
    }

    override fun getTvShows(table: String, sort: String): LiveData<Resource<PagedList<TvShowEntity>>> =
        object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(table, sort), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResultsItem>>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: List<TvShowResultsItem>) {
                val list = ArrayList<TvShowEntity>()
                data.forEach { entity ->
                    val movie = TvShowEntity(
                        entity.id,
                        entity.name,
                        ApiConfig.BASE_IMAGE_URL + entity.posterPath,
                        entity.firstAirDate,
                        entity.overview
                    )
                    list.add(movie)
                }
                localDataSource.insertTvShows(list)
            }
        }.asLiveData()

    override fun getTvShowDetail(id: Int): LiveData<Resource<DetailEntity>> =
        object : NetworkBoundResource<DetailEntity, TvShowDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailEntity> = localDataSource.getTvShowDetail(id)

            override fun shouldFetch(data: DetailEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getTvShowDetail(id)

            override fun saveCallResult(data: TvShowDetailResponse) {
                val tvShow = DetailEntity(
                    data.id,
                    data.name,
                    ApiConfig.BASE_IMAGE_URL + data.posterPath,
                    data.firstAirDate,
                    "${data.episodeRunTime[0] / 60}h ${data.episodeRunTime[0] % 60}m",
                    data.genres.joinToString { it.name },
                    data.overview,
                    MovieType.TV_SHOW_TYPE
                )
                localDataSource.insertDetail(tvShow)
            }
        }.asLiveData()

    override fun getTvShowFavorites(): LiveData<PagedList<DetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getTvShowFavorites(), config).build()
    }

    override fun setDetails(detail: DetailEntity, value: Boolean) =
            appExecutors.diskIO().execute { localDataSource.setMovieFavorite(detail, value) }

}