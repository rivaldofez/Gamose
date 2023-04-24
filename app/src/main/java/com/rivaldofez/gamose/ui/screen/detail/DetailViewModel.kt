package com.rivaldofez.gamose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldofez.gamose.data.GameRepository
import com.rivaldofez.gamose.domain.model.DetailGame
import com.rivaldofez.gamose.domain.model.Game
import com.rivaldofez.gamose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: GameRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<DetailGame>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<DetailGame>> get() = _uiState

    fun getDetailGame(gameId: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getDetailGame(gameId = gameId))
        }
    }
}