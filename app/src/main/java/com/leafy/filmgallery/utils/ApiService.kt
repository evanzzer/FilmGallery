package com.leafy.filmgallery.utils

import com.leafy.filmgallery.data.source.remote.response.moviedetail.MovieDetailResponse
import com.leafy.filmgallery.data.source.remote.response.movieresponse.MovieResponse
import com.leafy.filmgallery.data.source.remote.response.tvshowdetail.TvShowDetailResponse
import com.leafy.filmgallery.data.source.remote.response.tvshowresponse.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/popular")
    suspend fun getMovies(
            @Query("api_key") api: String
    ): Response<MovieResponse>

    @GET("3/movie/{id}")
    suspend fun getMovieDetails(
            @Path("id") id: Int,
            @Query("api_key") api: String
    ): Response<MovieDetailResponse>

    @GET("3/tv/popular")
    suspend fun getTvShows(
            @Query("api_key") api: String
    ): Response<TvShowResponse>

    @GET("3/tv/{id}")
    suspend fun getTvShowDetails(
            @Path("id") id: Int,
            @Query("api_key") api: String
    ): Response<TvShowDetailResponse>
}