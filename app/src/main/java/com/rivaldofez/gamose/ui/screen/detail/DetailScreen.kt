package com.rivaldofez.gamose.ui.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.rivaldofez.gamose.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rivaldofez.gamose.ui.common.UiState
import com.rivaldofez.gamose.ui.screen.detail.DetailViewModel
import com.rivaldofez.gamose.ui.screen.home.HomeViewModel
import com.rivaldofez.gamose.ui.theme.GamoseTheme

@Composable
fun DetailScreen(
    gameId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getDetailGame(gameId = gameId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    title = data.title,
                    thumbnail = data.thumbnail,
                    releaseDate = data.releaseDate,
                    genre = data.genre,
                    publisher = data.publisher,
                    developer = data.developer,
                    platform = data.platform,
                    description = data.description,
                    onBackClick = navigateBack)
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
                    painter = painterResource(R.drawable.dummy),
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
                        .clickable { onBackClick }
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
                    color = MaterialTheme.colors.secondary
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