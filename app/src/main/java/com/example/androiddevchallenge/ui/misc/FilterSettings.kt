package com.example.androiddevchallenge.ui.misc

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.ViewState

@ExperimentalFoundationApi
@Composable
fun FilterSettings(height: Dp, viewState: ViewState) =
    Surface(
        elevation = 2.dp,
        color = MaterialTheme.colors.surface,
        modifier = Modifier.height(height)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    tint = Color.Red,
                    contentDescription = "favorites icon"
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Only show favorites")
                Spacer(modifier = Modifier.weight(1f))
                Checkbox(
                    checked = viewState.showOnlyFavorites.value,
                    onCheckedChange = viewState::toggleShowFavoritesOnly,
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colors.primary,
                        uncheckedColor = MaterialTheme.colors.primaryVariant
                    )
                )
            }
            Divider(modifier = Modifier.height(1.dp))
            Text(text = "Select traits to filter by")
            Surface(
                modifier = Modifier
                    .weight(1F), color = MaterialTheme.colors.background
            ) {
                LazyVerticalGrid(
                    cells = GridCells.Adaptive(minSize = 104.dp)
                ) {
                    items(viewState.selectedTraits.toList()) { TraitChip(it) }
                }
            }
        }

    }
