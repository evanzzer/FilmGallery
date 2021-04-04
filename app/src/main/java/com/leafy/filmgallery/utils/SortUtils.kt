package com.leafy.filmgallery.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val tblMovie = "MovieEntity"
    const val tblTvShow = "TvShowEntity"

    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val NAME = "Name"

    fun getSortedQuery(table: String, filter: String): SimpleSQLiteQuery {
        val query = StringBuilder().append("SELECT * from $table ")
        when(filter) {
            NEWEST -> query.append("ORDER BY date DESC")
            OLDEST -> query.append("ORDER BY date ASC")
            NAME -> query.append("ORDER BY title ASC")
        }
        return SimpleSQLiteQuery(query.toString())
    }
}