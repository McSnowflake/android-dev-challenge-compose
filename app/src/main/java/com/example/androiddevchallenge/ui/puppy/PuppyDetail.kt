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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.ViewState
import com.example.androiddevchallenge.ui.misc.Image
import com.example.androiddevchallenge.ui.misc.LikeButton

@ExperimentalAnimationApi
@Composable
fun PuppyDetail(
    viewState: ViewState,
    onDeselect: () -> Unit
) =
    AnimatedVisibility(
        visible = true,
        enter = expandVertically(
            expandFrom = Alignment.CenterVertically,
            animationSpec = tween(),
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.CenterVertically
        ),
        initiallyVisible = false
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            TopAppBar(
                title = { Text(text = viewState.selectedPuppy.value!!.name) },
                backgroundColor = MaterialTheme.colors.surface,
                navigationIcon = {
                    IconButton(onClick = onDeselect) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Close puppy.value Detail View"
                        )
                    }
                },
                actions = { LikeButton(puppy = viewState.selectedPuppy.value!!) }
            )
            Divider()
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3F)
                            .horizontalScroll(rememberScrollState()),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            8.dp,
                            Alignment.CenterHorizontally
                        )
                    ) {
                        viewState.selectedPuppy.value!!.imgUrls.toList().map {
                            Image(
                                it,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(5.dp)),
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .weight(5F)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${viewState.selectedPuppy.value!!.name} is a ${viewState.selectedPuppy.value!!.age} months old ${viewState.selectedPuppy.value!!.breed}",
                            fontSize = 18.sp,
                            modifier = Modifier.paddingFromBaseline(top = 8.dp),
                            fontWeight = Bold
                        )
                        Text(
                            text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
                            fontSize = 14.sp,
                            modifier = Modifier.paddingFromBaseline(top = 20.dp)
                        )
                        Text(
                            text = "Traits: ${viewState.selectedPuppy.value!!.traits.joinToString(", ")}",
                            fontSize = 14.sp,
                            modifier = Modifier.paddingFromBaseline(top = 20.dp)
                        )
                    }
                }
            }
        }
    }
