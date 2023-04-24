package com.rivaldofez.gamose.model

data class DetailGame(
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