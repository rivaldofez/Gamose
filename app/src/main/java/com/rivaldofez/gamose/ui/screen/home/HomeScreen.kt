package com.rivaldofez.gamose.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rivaldofez.gamose.domain.model.Game
import com.rivaldofez.gamose.ui.common.UiState
import com.rivaldofez.gamose.ui.components.GameItem
import com.rivaldofez.gamose.R
import com.rivaldofez.gamose.ui.components.ErrorContent

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getGames()
            }
            is UiState.Success -> {
                if ( uiState.data.isEmpty() ) {
                    ErrorContent(message = "There is No Data", image = R.drawable.empty)
                } else {
                    HomeContent(games = uiState.data, modifier = modifier, navigateToDetail = navigateToDetail)
                }
            }
            
            is UiState.Error -> {
                ErrorContent(message = "There is error occured, please try again", image = R.drawable.error)
            }
        }
    }

}



@Composable
fun HomeContent(
    games: List<Game>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Text(
                text = "Explore Games",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(games) {
                GameItem(image = it.thumbnail, title = it.title, genre = it.genre, modifier = Modifier.clickable{
                    navigateToDetail(it.id)
                })
            }
        }
    }
}

