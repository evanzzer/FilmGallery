package com.leafy.filmgallery.data.source.remote.response.moviedetail

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

		@field:SerializedName("overview")
		var overview: String = "",

		@field:SerializedName("release_date")
		var releaseDate: String = "",

		@field:SerializedName("genres")
		var genres: List<MovieGenresItem>,

		@field:SerializedName("runtime")
		var runtime: Int = 0,

		@field:SerializedName("id")
		var id: Int = 0,

		@field:SerializedName("title")
		var title: String = "",

		@field:SerializedName("poster_path")
		var posterPath: String = ""
)