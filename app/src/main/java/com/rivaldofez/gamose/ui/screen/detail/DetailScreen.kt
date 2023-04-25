package com.rivaldofez.gamose.ui.screen.detail

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.rivaldofez.gamose.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.rivaldofez.gamose.ui.common.UiState
import com.rivaldofez.gamose.ui.components.ErrorContent
import com.rivaldofez.gamose.ui.theme.GamoseTheme

@Composable
fun DetailScreen(
    gameId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getDetailGame(gameId = gameId)
            }
            is UiState.Success -> {
                val data = uiState.data
                Scaffold (
                    floatingActionButton = {
                    FloatingActionButton(onClick = {
                        data.isFavorite = !data.isFavorite
                        viewModel.insertFavoriteGame(gameDetail = data)
                        if (data.isFavorite) {
                            showToast(
                                context = context,
                                message = R.string.added_to_favorite
                            )
                        } else {
                            showToast(
                                context = context,
                                message = R.string.removed_from_favorite
                            )
                        }
                    }) {
                        if (data.isFavorite){
                            Icon(painter = painterResource(id = R.drawable.ic_favorite_filled), contentDescription = null, tint = Color.White)
                        } else {
                            Icon(painter = painterResource(id = R.drawable.ic_favorite_unfilled), contentDescription = null, tint = Color.White)
                        }
                    }
                },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
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

            is UiState.Error -> {
                ErrorContent(message = stringResource(R.string.error_exception), image = R.drawable.error)
            }
        }
    }
}

private fun showToast(context: Context, message: Int){
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
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
                contentDescription = stringResource(R.string.cd_back_button),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
                    .clip(RoundedCornerShape(size = 8.dp))
                    .background(color = Color.White)
                    .padding(8.dp)

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
                text = stringResource(R.string.release_date),
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
                text = stringResource(R.string.platform),
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
                text = stringResource(R.string.publisher),
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
                text = stringResource(R.string.developer),
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
                text = stringResource(R.string.description),
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