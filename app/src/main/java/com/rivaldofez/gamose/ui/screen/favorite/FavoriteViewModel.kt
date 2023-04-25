package com.rivaldofez.gamose.ui.screen.favorite

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
class FavoriteViewModel @Inject constructor(private val gameRepository: GameRepository): ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<GameDetail>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<GameDetail>>> get() = _uiState

    fun getFavoriteGames() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            try {
                gameRepository.getFavoriteGames()
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