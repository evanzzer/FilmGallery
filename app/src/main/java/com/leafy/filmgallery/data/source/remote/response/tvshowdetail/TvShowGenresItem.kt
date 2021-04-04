package com.leafy.filmgallery.data.source.remote.response.tvshowdetail

import com.google.gson.annotations.SerializedName

data class TvShowGenresItem(

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("id")
		val id: Int
)