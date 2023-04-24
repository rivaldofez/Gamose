package com.rivaldofez.gamose.data.remote

import com.rivaldofez.gamose.data.remote.model.GameItemResponse
import retrofit2.Response
import retrofit2.http.GET

interface GameApi {
    @GET("games")
    suspend fun getGames(): Response<List<GameItemResponse>>
}