package com.rivaldofez.gamose.domain.model

import com.rivaldofez.gamose.data.local.model.GameFavoriteEntity

data class GameFavorite(
    var id: Int,
    var isFavorite: Boolean
)

fun GameFavoriteEntity.toGameFavorite() = GameFavorite(
    id, isFavorite
)
