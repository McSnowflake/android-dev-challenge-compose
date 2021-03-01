/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.puppy

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Trait
import com.example.androiddevchallenge.data.ViewState
import com.example.androiddevchallenge.ui.misc.FilterSettings
import java.util.UUID

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun PuppyList(viewState: ViewState, onSelect: (UUID) -> Unit) = Column {
    val filterHeight: Dp by animateDpAsState(if (viewState.showFilters.value) 240.dp else 0.dp)
    TopAppBar(
        navigationIcon = { Spacer(modifier = Modifier.width(16.dp)) },
        title = { Text(text = "Save the Puppy") },
        backgroundColor = MaterialTheme.colors.surface,
        actions = {
            IconButton(onClick = { viewState.showFilters.value = !viewState.showFilters.value }) {
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
                .weight(3F),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                (
                    if (viewState.selectedTraits.any { it.isSelected.value }) viewState.puppies.filter {
                        viewState.selectedTraits.hasAll(it.traits)
                    }
                    else viewState.puppies
                    )
                    .filter { if (viewState.showOnlyFavorites.value) it.favorite.value else true }
                    .map { PuppyItem(it, onSelect) }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

private fun List<Trait>.hasAll(traits: List<String>) = this
    .filter { it.isSelected.value }
    .all { traits.contains(it.name) }
