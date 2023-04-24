package com.rivaldofez.gamose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldofez.gamose.data.GameRepository
import com.rivaldofez.gamose.domain.model.GameDetail
import com.rivaldofez.gamose.domain.model.GameFavorite
import com.rivaldofez.gamose.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val gameRepository: GameRepository
    ) : ViewModel() {
    private val _uiStateGameDetail: MutableStateFlow<UiState<GameDetail>> = MutableStateFlow(UiState.Loading)
    val uiStateGameDetail: StateFlow<UiState<GameDetail>> get() = _uiStateGameDetail

    private val _uiStateGameFavorite: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.Loading)
    val uiStateGameFavorite: StateFlow<UiState<Boolean>> get() = _uiStateGameFavorite

    fun getDetailGame(gameId: Int) {
        viewModelScope.launch {
            gameRepository.getGameDetail(gameId = gameId)
                .catch {
                    _uiStateGameDetail.value = UiState.Error(it.message.toString())
                }
                .collect {
                    if(it == null){
                        _uiStateGameDetail.value = UiState.Error("Found null values")
                    } else {
                        _uiStateGameDetail.value = UiState.Success(it)
                    }
                }
        }
    }

    fun insertFavoriteGame(gameId: Int, isFavorite: Boolean){
        viewModelScope.launch {
            gameRepository.insertFavoriteGame(gameId = gameId, isFavorite = isFavorite)
        }
    }

    fun getFavoriteGame(gameId: Int){
        viewModelScope.launch {
            gameRepository.getFavoriteGame(gameId = gameId)
                .catch {
                    _uiStateGameFavorite.value = UiState.Error(it.message.toString())
                }
                .collect {
                    if(it == null || it.isFavorite == false){
                        _uiStateGameFavorite.value = UiState.Success(false)
                    } else {
                        _uiStateGameFavorite.value = UiState.Success(true)
                    }
                }
        }
    }
}