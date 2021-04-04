package com.leafy.filmgallery.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TvShowEntity")
data class TvShowEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "description")
    val description: String
)