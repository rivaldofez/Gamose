package com.rivaldofez.gamose.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rivaldofez.gamose.di.Injection
import com.rivaldofez.gamose.model.Game
import com.rivaldofez.gamose.ui.common.UiState
import com.rivaldofez.gamose.ui.common.ViewModelFactory
import com.rivaldofez.gamose.ui.components.GameItem
import com.rivaldofez.gamose.ui.screen.home.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getGames()
            }
            is UiState.Success -> {
                HomeContent(games = uiState.data, modifier = modifier, navigateToDetail = navigateToDetail)
            }
            is UiState.Error -> {}
        }
    }

}

@Composable
fun HomeContent(
    games: List<Game>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(games) {
            GameItem(image = it.thumbnail, title = it.title, genre = it.genre, modifier = Modifier.clickable{
                navigateToDetail(it.id)
            })
        }
    }
}