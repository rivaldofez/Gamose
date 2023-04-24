package com.rivaldofez.gamose.di

import android.content.Context
import androidx.room.Room
import com.rivaldofez.gamose.data.local.GameDao
import com.rivaldofez.gamose.data.local.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    fun providesGameDao(gameDatabase: GameDatabase): GameDao = gameDatabase.gameDao()

    @Provides
    @Singleton
    fun provideGameDatabase(@ApplicationContext context: Context): GameDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            GameDatabase::class.java,
            "game_database"
        ).build()
    }
}