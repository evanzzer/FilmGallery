package com.leafy.filmgallery.data.source.remote.response.moviedetail

import com.google.gson.annotations.SerializedName

data class MovieGenresItem(

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("id")
		val id: Int
)