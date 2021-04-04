package com.leafy.filmgallery.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "DetailEntity",
        primaryKeys = ["id", "type"])
data class DetailEntity(
        @NonNull
        @ColumnInfo(name = "id")
        val id: Int,

        @NonNull
        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "imageUrl")
        val imageUrl: String,

        @ColumnInfo(name = "date")
        val date: String,

        @ColumnInfo(name = "duration")
        val duration: String,

        @ColumnInfo(name = "category")
        val category: String,

        @ColumnInfo(name = "description")
        val description: String,

        @NonNull
        @ColumnInfo(name = "type")
        val type: Int,

        @ColumnInfo(name = "favorite")
        var favorite: Boolean = false
)