package com.rivaldofez.gamose.data

import com.rivaldofez.gamose.data.local.GameDatabase
import com.rivaldofez.gamose.data.local.model.GameFavoriteEntity
import com.rivaldofez.gamose.data.remote.GameService
import com.rivaldofez.gamose.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GameRepository @Inject constructor(
    private val gameService: GameService,
    private val gameDatabase: GameDatabase
    ) {

    suspend fun getAllGame(): Flow<List<Game>> {
        return flowOf(gameService.getGames().map {
            it.toGame()
        })
    }

    suspend fun getGameDetail(gameId: Int): Flow<GameDetail?>{
        return flowOf(gameService.getGameDetail(gameId)?.toGameDetail() ?: null)
    }

    fun getFavoriteGame(gameId: Int): Flow<GameFavorite?>{
        return gameDatabase.gameDao().getFavoriteGame(gameId = gameId).map {
            it.toGameFavorite()
        }
    }

    suspend fun insertFavoriteGame(gameId: Int, isFavorite: Boolean = false){
        gameDatabase.gameDao().insertFavoriteGame(GameFavoriteEntity(id = gameId, isFavorite = isFavorite))
    }

}