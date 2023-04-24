package com.rivaldofez.gamose.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rivaldofez.gamose.data.GameRepository
import com.rivaldofez.gamose.ui.screen.detail.DetailViewModel
import com.rivaldofez.gamose.ui.screen.home.HomeViewModel

//class ViewModelFactory(private val repository: GameRepository): ViewModelProvider.NewInstanceFactory() {
//
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            return HomeViewModel(repository) as T
//        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
//            return DetailViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//    }
//
//}