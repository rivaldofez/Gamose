package com.rivaldofez.gamose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rivaldofez.gamose.data.GameRepository
import com.rivaldofez.gamose.domain.model.Game
import com.rivaldofez.gamose.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( private val gameRepository: GameRepository ): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Game>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Game>>> get() = _uiState

    fun getGames() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                gameRepository.getAllGame()
                    .catch {
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        _uiState.value = UiState.Success(it)
                    }
            } catch (e: Exception){
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
}