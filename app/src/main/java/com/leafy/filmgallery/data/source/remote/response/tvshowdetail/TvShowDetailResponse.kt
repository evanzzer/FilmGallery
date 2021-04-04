package com.leafy.filmgallery.data.source.remote.response.tvshowdetail

import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(

		@field:SerializedName("first_air_date")
		val firstAirDate: String,

		@field:SerializedName("overview")
		val overview: String,

		@field:SerializedName("genres")
		val genres: List<TvShowGenresItem>,

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("episode_run_time")
		val episodeRunTime: List<Int>,

		@field:SerializedName("id")
		val id: Int,

		@field:SerializedName("poster_path")
		val posterPath: String
)