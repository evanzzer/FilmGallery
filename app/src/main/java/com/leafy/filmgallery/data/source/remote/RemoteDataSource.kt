package com.leafy.filmgallery.data.source.remote

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leafy.filmgallery.BuildConfig
import com.leafy.filmgallery.data.source.remote.response.moviedetail.MovieDetailResponse
import com.leafy.filmgallery.data.source.remote.response.movieresponse.MovieResultsItem
import com.leafy.filmgallery.data.source.remote.response.tvshowdetail.TvShowDetailResponse
import com.leafy.filmgallery.data.source.remote.response.tvshowresponse.TvShowResultsItem
import com.leafy.filmgallery.utils.ApiConfig
import com.leafy.filmgallery.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

class RemoteDataSource {
    companion object {
        const val NETWORK_ERROR = "Connection has not been established"
        const val NULL_ERROR = "No Data has been received"
        const val ERROR_403 = "403 - Access to the Server has been denied"
        const val ERROR_404 = "404 - Server not found"
        const val ERROR_500 = "500 - Internal Server Error"
        const val ERROR_502 = "502 - Bad Gateaway"
        const val ERROR_503 = "503 - Service Unavailable"
        const val ERROR_000 = "Unknown Error has occurred"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
                instance ?: synchronized(this) {
                    instance ?: RemoteDataSource()
                }
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieResultsItem>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val response = ApiConfig.getApiService().getMovies(BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    val movieResponse = response.body()?.results
                    result.postValue(
                            if (movieResponse != null) ApiResponse.success(movieResponse)
                            else ApiResponse.empty(NULL_ERROR, ArrayList())
                    )
                } else {
                    result.postValue(
                            ApiResponse.error(when (response.code()) {
                                403 -> ERROR_403
                                404 -> ERROR_404
                                500 -> ERROR_500
                                502 -> ERROR_502
                                503 -> ERROR_503
                                else -> ERROR_000
                            }, ArrayList())
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                result.postValue(when (e) {
                    is UnknownHostException, is ConnectException -> ApiResponse.error(NETWORK_ERROR, ArrayList())
                    else -> throw e
                })
            }
            EspressoIdlingResource.decrement()
        }
        return result
    }

    fun getMovieDetail(id: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val response = ApiConfig.getApiService().getMovieDetails(id, BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    val details = response.body()
                    result.postValue(
                            if (details != null) ApiResponse.success(details)
                            else ApiResponse.empty(NULL_ERROR, MovieDetailResponse("", "", ArrayList(), 0, 0, NULL_ERROR))
                    )
                } else {
                    result.postValue(
                            ApiResponse.error(when (response.code()) {
                                403 -> ERROR_403
                                404 -> ERROR_404
                                500 -> ERROR_500
                                502 -> ERROR_502
                                503 -> ERROR_503
                                else -> ERROR_000
                            }, MovieDetailResponse("", "", ArrayList(), 0, 0, ""))
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                result.postValue(when (e) {
                    is UnknownHostException, is ConnectException -> ApiResponse.error(NETWORK_ERROR,
                            MovieDetailResponse("", "", ArrayList(), 0, 0, ""))
                    else -> throw e
                })
            }
            EspressoIdlingResource.decrement()
        }
        return result
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShowResultsItem>>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<List<TvShowResultsItem>>>()
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val response = ApiConfig.getApiService().getTvShows(BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    val tvShowResponse = response.body()?.results
                    result.postValue(
                            if (tvShowResponse != null) ApiResponse.success(tvShowResponse)
                            else ApiResponse.empty(NULL_ERROR, ArrayList())
                    )
                } else {
                    result.postValue(
                            ApiResponse.error(when (response.code()) {
                                403 -> ERROR_403
                                404 -> ERROR_404
                                500 -> ERROR_500
                                502 -> ERROR_502
                                503 -> ERROR_503
                                else -> ERROR_000
                            }, ArrayList())
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                result.postValue(
                        when (e) {
                            is UnknownHostException, is ConnectException -> ApiResponse.error(NETWORK_ERROR, ArrayList())
                            else -> throw e
                        }
                )
            }
            EspressoIdlingResource.decrement()
        }
        return result
    }

    fun getTvShowDetail(id: Int): LiveData<ApiResponse<TvShowDetailResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TvShowDetailResponse>>()
        CoroutineScope(Dispatchers.Default).launch {
            try {
                val response = ApiConfig.getApiService().getTvShowDetails(id, BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    val details = response.body()
                    result.postValue(
                            if (details != null) ApiResponse.success(details)
                            else ApiResponse.empty(NULL_ERROR, TvShowDetailResponse("", "", ArrayList(), "", ArrayList(), 0, ""))
                    )
                } else {
                    result.postValue(
                            ApiResponse.error(when (response.code()) {
                                403 -> ERROR_403
                                404 -> ERROR_404
                                500 -> ERROR_500
                                502 -> ERROR_502
                                503 -> ERROR_503
                                else -> ERROR_000
                            }, TvShowDetailResponse("", "", ArrayList(), "", ArrayList(), 0, ""))
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                result.postValue(when (e) {
                    is UnknownHostException, is ConnectException -> ApiResponse.error(NETWORK_ERROR,
                            TvShowDetailResponse("", "", ArrayList(), "", ArrayList(), 0, ""))
                    else -> throw e
                })
            }
            EspressoIdlingResource.decrement()
        }
        return result
    }
}