package com.rivaldofez.gamose.data.remote

import com.rivaldofez.gamose.data.remote.model.GameItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameService @Inject constructor(private val gameApi: GameApi) {

    suspend fun getGames() : List<GameItemResponse> {
        return withContext(Dispatchers.IO){
            val games = gameApi.getGames()
            games.body() ?: emptyList()
        }
    }
}