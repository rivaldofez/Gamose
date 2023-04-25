package com.rivaldofez.gamose.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.rivaldofez.gamose.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.rivaldofez.gamose.ui.common.UiState
import com.rivaldofez.gamose.ui.screen.detail.DetailViewModel
import com.rivaldofez.gamose.ui.theme.GamoseTheme
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun DetailScreen(
    gameId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    viewModel.uiStateGameDetail.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getDetailGame(gameId = gameId)
            }
            is UiState.Success -> {
                var data = uiState.data
                Scaffold (floatingActionButton = {
                    FloatingActionButton(onClick = {
                        data.isFavorite = !data.isFavorite
                        viewModel.insertFavoriteGame(gameDetail = data)
                    }) {
                        if (data.isFavorite){
                            Icon(painter = painterResource(id = R.drawable.ic_favorite_filled), contentDescription = null)
                        } else {
                            Icon(painter = painterResource(id = R.drawable.ic_favorite_unfilled), contentDescription = null)
                        }
                    }
                }) { innerPadding ->
                    DetailContent(
                        title = data.title,
                        thumbnail = data.thumbnail,
                        releaseDate = data.releaseDate,
                        genre = data.genre,
                        publisher = data.publisher,
                        developer = data.developer,
                        platform = data.platform,
                        description = data.description,
                        onBackClick = navigateBack,
                        modifier = Modifier.padding(innerPadding)
                        )

                }

            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    title: String,
    thumbnail: String,
    releaseDate: String,
    genre: String,
    publisher: String,
    developer: String,
    platform: String,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            Image(
                painter = rememberImagePainter(data = thumbnail),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Button",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )

            Text(
                text = genre,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                color = MaterialTheme.colors.secondary,
                modifier = modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Release Date :",
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Start,
                modifier = modifier.fillMaxWidth()
            )

            Text(
                text = releaseDate,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "Platform :",
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Justify,
                modifier = modifier.fillMaxWidth()
            )

            Text(
                text = platform,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "Publisher :",
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Justify,
                modifier = modifier.fillMaxWidth()
            )

            Text(
                text = publisher,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "Developer :",
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Justify,
                modifier = modifier.fillMaxWidth()
            )

            Text(
                text = developer,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "Description :",
                style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Justify,
                modifier = modifier.fillMaxWidth()
            )

            Text(
                text = description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Justify
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    GamoseTheme {
        DetailContent(
            title = "GTA V San Andreas" ,
            thumbnail = "",
            releaseDate = "11 Februari 2022",
            genre = "Action",
            publisher = "Rockstar",
            developer = "Gameloft",
            platform = "Windows",
            description = "Lorem ipsum dolor sit amet",
            onBackClick = { }
        )
    }
}