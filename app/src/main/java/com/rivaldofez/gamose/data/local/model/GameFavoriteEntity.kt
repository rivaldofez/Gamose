package com.rivaldofez.gamose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_favorites")
data class GameFavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var isFavorite: Boolean
)