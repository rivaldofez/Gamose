package com.rivaldofez.gamose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rivaldofez.gamose.domain.model.GameDetail

@Entity(tableName = "game_detail")
data class GameDetailEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    val shortDescription: String,
    val thumbnail: String,
    val freetogameProfileUrl: String,
    val description: String,
    val title: String,
    val platform: String,
    val gameUrl: String,
    val releaseDate: String,
    val genre: String,
    val publisher: String,
    val developer: String,
    val status: String,
    var isFavorite: Boolean
)

//fun GameDetail.toGameDetailEntity() = GameDetailEntity(
//    id, shortDescription, thumbnail, freetogameProfileUrl, description, title, platform, gameUrl, releaseDate, genre, publisher, developer, status, isFavorite
//)

fun GameDetail.toGameDetailEntity() = GameDetailEntity(
    id, shortDescription, thumbnail, freetogameProfileUrl, description, title, platform, gameUrl, releaseDate, genre, publisher, developer, status, isFavorite
)