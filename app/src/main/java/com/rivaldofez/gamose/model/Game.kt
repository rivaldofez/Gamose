package com.rivaldofez.gamose.model

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