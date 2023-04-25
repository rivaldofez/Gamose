package com.rivaldofez.gamose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rivaldofez.gamose.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),

        ) {
            TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
                Text(
                    text = "Profile",
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }


            Image(
                painter = painterResource(R.drawable.profile_pict),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(250.dp)
                    .padding(32.dp)
                    .clip(RoundedCornerShape(16.dp))

            )

            Text(
                text = "Rivaldo Fernandes",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )

            Text(
                text = "rivaldofez@gmail.com",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                color = MaterialTheme.colors.secondary
            )
        }


}