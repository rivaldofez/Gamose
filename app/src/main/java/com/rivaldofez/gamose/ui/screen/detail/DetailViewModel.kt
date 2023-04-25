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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val gameRepository: GameRepository
    ) : ViewModel() {
    private var _uiStateGameDetail: MutableStateFlow<UiState<GameDetail>> = MutableStateFlow(UiState.Loading)
    val uiStateGameDetail: StateFlow<UiState<GameDetail>> get() = _uiStateGameDetail

    fun getDetailGame(gameId: Int) {


        viewModelScope.launch {

            gameRepository.getFavoriteGame(gameId = gameId)
                .catch {
                    _uiStateGameDetail.value = UiState.Error(it.message.toString())
                }
                .collect { gameDetailDb ->
                    if(gameDetailDb == null){
                        gameRepository.getGameDetail(gameId = gameId)
                            .catch {
                                _uiStateGameDetail.value = UiState.Error(it.message.toString())
                            }
                            .collect { gameDetailRm ->
                                if(gameDetailRm == null){
                                    _uiStateGameDetail.value = UiState.Error("Found null values")
                                } else {
                                    _uiStateGameDetail.value = UiState.Success(gameDetailRm)
                                    gameRepository.insertFavoriteGame(gameDetailRm)
                                }
                            }
                    } else {
                        _uiStateGameDetail.value = UiState.Success(gameDetailDb)
                        Log.d("Bacot", gameDetailDb.toString())
                    }
                }
        }
    }

    fun insertFavoriteGame(gameDetail: GameDetail){
        _uiStateGameDetail.value = UiState.Loading
        viewModelScope.launch {
            gameRepository.insertFavoriteGame(gameDetail = gameDetail)
            getDetailGame(gameDetail.id)
        }
    }
}