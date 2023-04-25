package com.rivaldofez.gamose.data

import com.rivaldofez.gamose.data.local.GameDatabase
import com.rivaldofez.gamose.data.local.model.toGameDetailEntity
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
        return flowOf(gameService.getGameDetail(gameId)?.toGameDetail())
    }

    fun getFavoriteGameById(gameId: Int): Flow<GameDetail?>{
        return gameDatabase.gameDao().getFavoriteGameById(gameId = gameId).map {
            it?.toGameDetail()
        }
    }

    fun getFavoriteGames(): Flow<List<GameDetail>>{
        return gameDatabase.gameDao().getFavoriteGames().map { listGameEntity ->
            listGameEntity.map {
                it.toGameDetail()
            }
        }
    }

    suspend fun insertFavoriteGame(gameDetail: GameDetail){
        gameDatabase.gameDao().insertFavoriteGame(gameDetailEntity = gameDetail.toGameDetailEntity())
    }

}