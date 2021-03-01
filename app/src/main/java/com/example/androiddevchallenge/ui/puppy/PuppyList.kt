package com.example.androiddevchallenge.ui.puppy

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Puppy
import com.example.androiddevchallenge.data.Trait
import com.example.androiddevchallenge.data.ViewState
import com.example.androiddevchallenge.ui.misc.FilterSettings

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun PuppyList(    viewState: ViewState) = Column {
    val filterHeight: Dp by animateDpAsState(if (viewState.showFilters.value) 240.dp else 0.dp)
    TopAppBar(
        navigationIcon = { Spacer(modifier = Modifier.width(16.dp)) },
        title = { Text(text = "Save the Puppy") },
        backgroundColor = MaterialTheme.colors.surface,
        actions = {
            IconButton(onClick = viewState::toggleShowFilters) {
                Icon(
                    Icons.Default.FilterList, "open filters",
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }
    )
    Divider(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.background)
    Column {

        FilterSettings(filterHeight, viewState)
        Surface(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(3F),
            color = MaterialTheme.colors.background
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                (if (viewState.selectedTraits.any { it.isSelected.value }) viewState.puppies.filter {
                    viewState.selectedTraits.hasAnyTrait(it.traits)
                }
                else viewState.puppies)
                    .filter { if (viewState.showOnlyFavorites.value) it.favorite.value else true }
                    .map { PuppyItem(it, viewState)}
                Spacer(modifier = Modifier.height(4.dp))
            }

        }
    }
}

private fun List<Trait>.hasAnyTrait(traits: List<String>) = this
    .filter { it.isSelected.value }
    .any { traits.contains(it.name) }
