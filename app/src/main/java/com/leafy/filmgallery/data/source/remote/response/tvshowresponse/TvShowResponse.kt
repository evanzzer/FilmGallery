package com.leafy.filmgallery.data.source.remote.response.tvshowresponse

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

		@field:SerializedName("page")
		val page: Int,

		@field:SerializedName("total_pages")
		val totalPages: Int,

		@field:SerializedName("results")
		val results: List<TvShowResultsItem>,

		@field:SerializedName("total_results")
		val totalResults: Int
)