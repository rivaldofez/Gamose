package com.rivaldofez.gamose.domain.model

import com.rivaldofez.gamose.data.remote.model.GameDetailResponse
import com.rivaldofez.gamose.data.remote.model.GameItemResponse
import com.rivaldofez.gamose.ui.components.GameItem

data class GameDetail(
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
    val id: Int,
    val status: String
)

fun GameDetailResponse.toGameDetail() = GameDetail(
    shortDescription, thumbnail, freetogameProfileUrl, description, title, platform, gameUrl, releaseDate, genre, publisher, developer, id, status
)