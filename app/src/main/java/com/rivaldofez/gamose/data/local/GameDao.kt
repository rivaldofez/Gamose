package com.rivaldofez.gamose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rivaldofez.gamose.data.local.model.GameFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGame(favoriteEntity: GameFavoriteEntity)

    @Query("SELECT * FROM game_favorites WHERE id = :gameId LIMIT 1")
    fun getFavoriteGame(gameId: Int) : Flow<GameFavoriteEntity>
}