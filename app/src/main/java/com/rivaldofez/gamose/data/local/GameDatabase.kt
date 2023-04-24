package com.rivaldofez.gamose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rivaldofez.gamose.data.local.model.GameFavoriteEntity

@Database(
    entities = [GameFavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}