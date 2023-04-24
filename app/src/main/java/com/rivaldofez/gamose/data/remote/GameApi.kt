package com.rivaldofez.gamose.data.remote

import com.rivaldofez.gamose.data.remote.model.GameDetailResponse
import com.rivaldofez.gamose.data.remote.model.GameItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {
    @GET("games")
    suspend fun getGames(): Response<List<GameItemResponse>>

    @GET("game")
    suspend fun getGameDetail(
        @Query("id") gameId: Int
    ): Response<GameDetailResponse>


}