package com.rivaldofez.gamose.ui.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldofez.gamose.data.GameRepository
import com.rivaldofez.gamose.domain.model.GameDetail
import com.rivaldofez.gamose.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val gameRepository: GameRepository
    ) : ViewModel() {
    private var _uiState: MutableStateFlow<UiState<GameDetail>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<GameDetail>> get() = _uiState

    fun getDetailGame(gameId: Int) {
        try {
            viewModelScope.launch {
                gameRepository.getFavoriteGameById(gameId = gameId)
                    .catch {
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect { gameDetailDb ->
                        if(gameDetailDb == null){
                            gameRepository.getGameDetail(gameId = gameId)
                                .catch {
                                    _uiState.value = UiState.Error(it.message.toString())
                                }
                                .collect { gameDetailRm ->
                                    if(gameDetailRm == null){
                                        _uiState.value = UiState.Error("Found null values")
                                    } else {
                                        _uiState.value = UiState.Success(gameDetailRm)
                                        gameRepository.insertFavoriteGame(gameDetailRm)
                                    }
                                }
                        } else {
                            _uiState.value = UiState.Success(gameDetailDb)
                            Log.d("Bacot", gameDetailDb.toString())
                        }
                    }
            }
        } catch (e: Exception){
            _uiState.value = UiState.Error(e.message.toString())
        }
    }

    fun insertFavoriteGame(gameDetail: GameDetail){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            gameRepository.insertFavoriteGame(gameDetail = gameDetail)
        }
    }
}