package com.rivaldofez.gamose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.rivaldofez.gamose.ui.theme.GamoseTheme
import com.rivaldofez.gamose.ui.theme.Shapes

@Composable
fun GameItem(
    image: String,
    title: String,
    genre: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = rememberImagePainter(data = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )

        Text(
            text = genre,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
@Preview(showBackground = true)
fun GameItemPreview() {
    GamoseTheme {
        GameItem(image = "", title = "GTA V", genre = "Action")
    }
}