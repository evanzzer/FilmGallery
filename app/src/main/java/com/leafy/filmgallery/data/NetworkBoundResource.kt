package com.leafy.filmgallery.data

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.leafy.filmgallery.data.source.remote.ApiResponse
import com.leafy.filmgallery.data.source.remote.StatusResponse
import com.leafy.filmgallery.utils.AppExecutors
import com.leafy.filmgallery.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val executors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.postValue(Resource.loading(null))

        val dbSource by lazy { loadFromDB() }

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) fetchFromNetwork(dbSource)
            else result.addSource(dbSource) { newData ->
                result.postValue(Resource.success(newData))
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.postValue(Resource.loading(newData))
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS -> executors.diskIO().execute {
                    saveCallResult(response.body)
                    Handler(Looper.getMainLooper()).post {
                        result.addSource(loadFromDB()) { newData ->
                            result.postValue(Resource.success(newData))
                        }
                    }
                }
                StatusResponse.EMPTY -> executors.mainThread().execute {
                    Handler(Looper.getMainLooper()).post {
                        result.addSource(loadFromDB()) { newData ->
                            result.postValue(Resource.empty(response.message, newData))
                        }
                    }
                }
                StatusResponse.ERROR -> Handler(Looper.getMainLooper()).post {
                    result.addSource(dbSource) { newData ->
                        result.postValue(Resource.error(response.message, newData))
                    }
                }
            }
        }
    }
}