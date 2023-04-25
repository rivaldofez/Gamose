package com.rivaldofez.gamose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rivaldofez.gamose.data.local.model.GameDetailEntity

@Database(
    entities = [GameDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}