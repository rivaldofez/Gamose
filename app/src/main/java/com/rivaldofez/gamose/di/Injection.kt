package com.rivaldofez.gamose.di

import com.rivaldofez.gamose.data.GameRepository

object Injection {
    fun provideRepository(): GameRepository {
        return GameRepository.getInstance()
    }
}