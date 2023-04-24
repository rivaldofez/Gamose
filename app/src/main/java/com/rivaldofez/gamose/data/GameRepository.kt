package com.rivaldofez.gamose.data

import com.rivaldofez.gamose.data.remote.GameService
import com.rivaldofez.gamose.domain.model.GameDetail
import com.rivaldofez.gamose.domain.model.Game
import com.rivaldofez.gamose.domain.model.toGame
import com.rivaldofez.gamose.domain.model.toGameDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class GameRepository @Inject constructor(private val gameService: GameService) {

    suspend fun getAllGame(): Flow<List<Game>> {
        return flowOf(gameService.getGames().map {
            it.toGame()
        })
    }

    suspend fun getGameDetail(gameId: Int): Flow<GameDetail?>{
        return flowOf(gameService.getGameDetail(gameId)?.toGameDetail() ?: null)
    }

}