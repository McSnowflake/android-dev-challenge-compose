package com.example.androiddevchallenge.ui.misc

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.data.Puppy

@Composable
fun LikeButton(puppy: Puppy, modifier: Modifier = Modifier) =
    IconButton(modifier = modifier, onClick = { puppy.favorite.value = !puppy.favorite.value }) {
        Icon(
            imageVector = if (puppy.favorite.value) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
            contentDescription = "",
            tint = if (puppy.favorite.value) Color.Red else MaterialTheme.colors.primaryVariant
        )
    }