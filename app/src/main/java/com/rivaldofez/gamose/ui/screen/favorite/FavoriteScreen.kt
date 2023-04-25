package com.rivaldofez.gamose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.rivaldofez.gamose.GamoseAppPreview
import com.rivaldofez.gamose.R
import com.rivaldofez.gamose.domain.model.GameDetail
import com.rivaldofez.gamose.ui.common.UiState
import com.rivaldofez.gamose.ui.screen.favorite.FavoriteViewModel
import com.rivaldofez.gamose.ui.theme.GamoseTheme
import com.rivaldofez.gamose.ui.theme.Shapes

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getFavoriteGames()
            }
            is UiState.Success -> {
                GameFavoriteContent(favoriteGames = uiState.data, navigateToDetail = navigateToDetail, modifier = modifier)
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun GameFavoriteContent(
    favoriteGames: List<GameDetail>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
){
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(favoriteGames) {
            GameFavoriteItem(image = it.thumbnail, title = it.title, genre = it.genre)
            Divider()
        }
    }
}

@Composable
fun GameFavoriteItem(
    image: String,
    title: String,
    genre: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.dummy),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Text(
                text = genre,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GameFavoriteItem() {
    GamoseTheme {
        GameFavoriteItem(image = "", title = "Hello", genre = "Action")
    }
}