package com.rivaldofez.gamose.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rivaldofez.gamose.data.local.model.GameDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGame(gameDetailEntity: GameDetailEntity)

    @Query("SELECT * FROM game_detail WHERE id = :gameId LIMIT 1")
    fun getFavoriteGameById(gameId: Int) : Flow<GameDetailEntity?>

    @Query("SELECT * FROM game_detail WHERE isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameDetailEntity>>
}