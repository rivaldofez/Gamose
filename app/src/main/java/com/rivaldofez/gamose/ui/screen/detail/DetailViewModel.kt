package com.rivaldofez.gamose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldofez.gamose.data.GameRepository
import com.rivaldofez.gamose.domain.model.Game
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
class DetailViewModel @Inject constructor( private val gameRepository: GameRepository ) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<GameDetail>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<GameDetail>> get() = _uiState

    fun getDetailGame(gameId: Int) {
        viewModelScope.launch {
            gameRepository.getGameDetail(gameId = gameId)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    if(it == null){
                        _uiState.value = UiState.Error("Found null values")
                    } else {
                        _uiState.value = UiState.Success(it)
                    }
                }
        }
    }
}