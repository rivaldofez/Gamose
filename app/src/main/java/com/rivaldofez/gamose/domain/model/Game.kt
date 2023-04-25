package com.rivaldofez.gamose.domain.model

import com.rivaldofez.gamose.data.remote.model.GameItemResponse

data class Game(

    val shortDescription: String,
    val thumbnail: String,
    val gameUrl: String,
    val releaseDate: String,
    val freetogameProfileUrl: String,
    val genre: String,
    val publisher: String,
    val developer: String,
    val id: Int,
    val title: String,
    val platform: String
)

fun GameItemResponse.toGame() = Game(
    shortDescription, thumbnail, gameUrl, releaseDate, freetogameProfileUrl, genre, publisher, developer, id, title, platform
)