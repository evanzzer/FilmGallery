package com.leafy.filmgallery.data.source.remote.response.tvshowresponse

import com.google.gson.annotations.SerializedName

data class TvShowResultsItem(

		@field:SerializedName("overview")
		val overview: String,

		@field:SerializedName("first_air_date")
		val firstAirDate: String,

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("id")
		val id: Int,

		@field:SerializedName("poster_path")
		val posterPath: String
)