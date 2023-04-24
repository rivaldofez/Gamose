package com.rivaldofez.gamose.data

import com.rivaldofez.gamose.data.remote.GameService
import com.rivaldofez.gamose.data.remote.model.GameItemResponse
import com.rivaldofez.gamose.domain.model.DetailGame
import com.rivaldofez.gamose.domain.model.Game
import com.rivaldofez.gamose.domain.model.toGame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


class GameRepository @Inject constructor(private val gameService: GameService) {
    fun getDetailGame(gameId: Int): DetailGame {
        return DetailGame(
            title = "Gta 5",
            id = 10,
            description = "Lorem ipsum dolor sit amet",
            shortDescription = "Lorem short",
            thumbnail = "",
            freetogameProfileUrl = "",
            platform = "",
            gameUrl = "",
            releaseDate = "",
            genre = "",
            publisher = "",
            developer = "",
            status = "",
        )
    }

    suspend fun getAllGame(): Flow<List<Game>> {
        return flowOf(gameService.getGames().map {
            it.toGame()
        })
    }

}