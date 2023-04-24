package com.rivaldofez.gamose.di

import com.rivaldofez.gamose.data.GameRepository

class Injection {
    fun provideRepository(): GameRepository {
        return GameRepository.getInstance()
    }
}