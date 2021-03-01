package com.example.androiddevchallenge.ui.misc

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.InsertPhoto
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun Image(
    imgUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillHeight
) =
    CoilImage(
        data = imgUrl,
        contentDescription = "",
        fadeIn = true,
        contentScale = contentScale,
        modifier = modifier,
        error = { Icon(Icons.Default.InsertPhoto, "image placeholder") }
    )
